package out_of_the_breach.GUI.componentSelectionStrategy;

import com.googlecode.lanterna.input.KeyStroke;
import out_of_the_breach.GUI.GUIcomponent;

import java.util.List;

public class ParentNodeSelectionStrategy implements iComponentSelectionStrategy {
    private int selectedComponent;

    public ParentNodeSelectionStrategy() {
        selectedComponent = 0;
    }

    @Override
    public boolean processKeystroke(KeyStroke stroke, List<GUIcomponent> components) {
        for (; selectedComponent < components.size(); selectedComponent++) {
            GUIcomponent component = components.get(selectedComponent);

            if (component.isSelectable()) {
                component.setSelected(true);
            } else {
                continue;
            }

            if (component.processKeystroke(stroke)) {
                return true;
            } else {
                component.setSelected(false);
            }
        }
        selectedComponent = 0;
        return false;
    }
}
