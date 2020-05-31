package out_of_the_breach.GUI.componentSelectionStrategy;

import com.googlecode.lanterna.input.KeyStroke;
import out_of_the_breach.GUI.GUIcomponent;

import java.util.List;

public interface iComponentSelectionStrategy {
    boolean processKeystroke(KeyStroke stroke, List<GUIcomponent> components);
}
