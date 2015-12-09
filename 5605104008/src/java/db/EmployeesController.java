package db;

import db.util.JsfUtil;
import db.util.PaginationHelper;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("employeesController")
@SessionScoped
public class EmployeesController implements Serializable {

    private Employees current;
    private DataModel items = null;
    @EJB
    private db.EmployeesFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public EmployeesController() {
    }

    public Employees getSelected() {
        if (current == null) {
            current = new Employees();
            current.setEmployeesPK(new db.EmployeesPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private EmployeesFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Employees) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Employees();
        current.setEmployeesPK(new db.EmployeesPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getEmployeesPK().setProjno(current.getProjwork().getProjworkPK().getProjno());
            current.getEmployeesPK().setLastName(current.getDetails().getDetailsPK().getLastName());
            current.getEmployeesPK().setName(current.getDetails().getDetailsPK().getName());
            current.getEmployeesPK().setEmpnum(current.getProjwork().getProjworkPK().getEmpnum());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("EmployeesCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Employees) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getEmployeesPK().setProjno(current.getProjwork().getProjworkPK().getProjno());
            current.getEmployeesPK().setLastName(current.getDetails().getDetailsPK().getLastName());
            current.getEmployeesPK().setName(current.getDetails().getDetailsPK().getName());
            current.getEmployeesPK().setEmpnum(current.getProjwork().getProjworkPK().getEmpnum());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("EmployeesUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Employees) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("EmployeesDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Employees getEmployees(db.EmployeesPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Employees.class)
    public static class EmployeesControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EmployeesController controller = (EmployeesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "employeesController");
            return controller.getEmployees(getKey(value));
        }

        db.EmployeesPK getKey(String value) {
            db.EmployeesPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new db.EmployeesPK();
            key.setEmpnum(values[0]);
            key.setProjno(values[1]);
            key.setName(values[2]);
            key.setLastName(values[3]);
            return key;
        }

        String getStringKey(db.EmployeesPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getEmpnum());
            sb.append(SEPARATOR);
            sb.append(value.getProjno());
            sb.append(SEPARATOR);
            sb.append(value.getName());
            sb.append(SEPARATOR);
            sb.append(value.getLastName());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Employees) {
                Employees o = (Employees) object;
                return getStringKey(o.getEmployeesPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Employees.class.getName());
            }
        }

    }

}
