package out_of_the_breach.GUI.componentSelectionStrategy;

import com.googlecode.lanterna.input.KeyStroke;
import out_of_the_breach.GUI.GUIcomponent;
import out_of_the_breach.GUI.componentSelectionStrategy.iComponentSelectionStrategy;

import java.util.List;

public class RootSelectionStrategy implements iComponentSelectionStrategy {
    private int selectedComponent;

    public RootSelectionStrategy() {
        selectedComponent = 0;
    }

    @Override
    public boolean processKeystroke(KeyStroke stroke, List<GUIcomponent> components) {
        boolean stopAtFirstSelectable = false;
        for (int i = 0; i < components.size(); i++, selectedComponent = (selectedComponent + 1) % components.size()) {
            GUIcomponent component = components.get(selectedComponent);

            if (component.isSelectable()) {
                component.setSelected(true);

                if (stopAtFirstSelectable) {
                    return true;
                }
            } else {
                continue;
            }

            if (component.processKeystroke(stroke)) {
                return true;
            } else {
                component.setSelected(false);
                stopAtFirstSelectable = true;
            }
        }
        selectedComponent = 0;
        return true;
    }

    public int getSelectedComponent() {
        return selectedComponent;
    }
}
