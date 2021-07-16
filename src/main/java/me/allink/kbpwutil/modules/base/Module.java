package me.allink.kbpwutil.modules.base;

import me.allink.kbpwutil.modules.settings.Settings;
import net.minecraft.text.TranslatableText;

import java.util.ArrayList;
import java.util.List;

public class Module {
    private static String translation;
    private static boolean toggled;
    private static List<Settings> settings = new ArrayList<Settings>();

    /**
     * Load the module
     *
     * @param name    Translation key for the module's name
     * @param toggled Whether or not the module is already toggled
     */
    public Module(String name, boolean toggled) {
        this.translation = name;
        this.toggled = toggled;
    }

    /**
     * Load the module (unimplemented!)
     *
     * @param name     Translation key for the module's name
     * @param toggled  Whether or not the module is already toggled
     * @param settings The Settings object of the module
     */

    protected Module(String name, boolean toggled, List<Settings> settings) {
        this.translation = name;
        this.toggled = toggled;
        this.settings = settings;
        throw new UnsupportedOperationException("This module constructor is not implemented yet!");
    }

    /**
     * Get the translated module name
     *
     * @return Returns the readable, translated module name
     */
    public String getFriendlyName() {
        return new TranslatableText(translation).toString();
    }

    /**
     * Get if module is toggled
     *
     * @return Toggle status
     */
    public boolean isToggled() {
        return toggled;
    }

    /**
     * Set the toggle status of the module
     *
     * @param toggled The new toggle status
     */

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
    }

    /**
     * Toggle the module
     *
     * @return The new toggle status
     */
    public boolean toggle() {
        this.toggled = !toggled;
        return this.toggled;
    }
}
