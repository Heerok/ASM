package digitalaxom.asm.view;

import javax.persistence.Transient;
import java.util.LinkedHashSet;
import java.util.Set;

public class MenuItem {

    private String link;
    private String label;
    private boolean divider;
    private String icon;
    private Set<MenuItem> submenu = new LinkedHashSet<>();
    private int orderByKey;
    private String privilege;

    @Transient
    private String parent;

    public MenuItem() {

    }

    public MenuItem(MenuItem mi) {
        this.label = mi.getLabel();
        this.link = mi.getLink();
        this.icon = mi.getIcon();
        this.divider = mi.isDivider();
        this.privilege = mi.getPrivilege();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Set<MenuItem> getSubmenu() {
        return submenu;
    }

    public void setSubmenu(Set<MenuItem> submenu) {
        this.submenu = submenu;
    }

    public boolean isDivider() {
        return divider;
    }

    public void setDivider(boolean divider) {
        this.divider = divider;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public void addSubMenu(MenuItem item) {
        submenu.add(item);
    }

    public MenuItem withLink(String link) {
        this.link = link;
        return this;
    }

    public MenuItem withLabel(String label) {
        this.label = label;
        return this;
    }

    public MenuItem withOrderByKey(int orderByKey) {
        this.orderByKey = orderByKey;
        return this;
    }

    public MenuItem withDivider() {
        divider = true;
        return this;
    }

    public int getOrderByKey() {
        return orderByKey;
    }

    public void setOrderByKey(int orderByKey) {
        this.orderByKey = orderByKey;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "link='" + link + '\'' +
                ", label='" + label + '\'' +
                ", icon='" + icon + '\'' +
                ", submenu=" + submenu +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuItem menuItem = (MenuItem) o;
        if (divider && !menuItem.divider) return false;
        if (link != null ? !link.equals(menuItem.link) : menuItem.link != null) return false;
        return label.equals(menuItem.label);

    }

    @Override
    public int hashCode() {
        if (divider) return 0x0f;
        int result = link != null ? link.hashCode() : 0;
        result = 31 * result + label.hashCode();
        return result;
    }

}
