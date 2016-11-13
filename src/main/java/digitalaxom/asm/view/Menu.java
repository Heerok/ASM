package digitalaxom.asm.view;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Menu {

    private Set<MenuItem> submenu = new LinkedHashSet<>();

    public void add(MenuItem item) {
        submenu.add(item);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "submenu=" + submenu +
                '}';
    }

    public void add(List<MenuItem> menu) {
        for (MenuItem item : menu) {
            if(submenu.contains(item)){
                Iterator<MenuItem> iterator = submenu.iterator();
                while(iterator.hasNext()){
                    MenuItem mainItem = iterator.next();
                    if(item.equals(mainItem)){
                        mainItem.getSubmenu().addAll(item.getSubmenu());
                        break;
                    }
                }
            }else {
                submenu.add(item);
            }
        }
    }

    public Set<MenuItem> getSubmenu() {
        return submenu;
    }

    public void setSubmenu(Set<MenuItem> submenu) {
        this.submenu = submenu;
    }

    /**
     * Add selective child menuItem to parent rather then copying all children of parent
     * @param parent
     * @param menuItem
     */
    public void add(MenuItem parent, MenuItem menuItem) {

        MenuItem addedParent = null;
        if(!submenu.contains(parent)){
            addedParent = new MenuItem(parent);
            submenu.add(addedParent);
        } else {
            for (MenuItem mi : submenu) {
                if (parent.equals(mi)) {
                    addedParent = mi;
                }
            }
        }

        if (addedParent != null) addedParent.getSubmenu().add(menuItem);

    }
}
