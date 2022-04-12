package de.tdf.helpy.methods;

import java.util.List;
import java.io.IOException;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.OfflinePlayer;
import java.io.File;
import org.bukkit.configuration.file.YamlConfiguration;

public class OldpCon
{
    YamlConfiguration config;
    File file;
    
    public OldpCon(final OfflinePlayer p, final String str) {
        final String uuid = p.getUniqueId().toString();
        this.file = new File("plugins/" + str + "/" + uuid + ".yml");
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }
    
    public YamlConfiguration getConfig() {
        return this.config;
    }
    
    public File getFile() {
        return this.file;
    }
    
    public static OldpCon loadConfig(final OfflinePlayer p, final String str) {
        if (!hasConfig(p, str)) {
            createConfig(p, str);
        }
        final OldpCon pOldpCon = new OldpCon(p, str);
        return pOldpCon;
    }
    
    public void setTrue(final String string) {
        this.config.set(string, (Object)true);
        this.savePCon();
    }
    
    public static boolean hasConfig(final OfflinePlayer p, final String str) {
        final String uuid = p.getUniqueId().toString();
        return new File("plugins/" + str + "/" + uuid + ".yml").exists();
    }
    
    public ConfigurationSection getConfigurationSection(final String string) {
        return this.config.getConfigurationSection(string);
    }
    
    public static OldpCon createConfig(final OfflinePlayer p, final String str) {
        final String uuid = p.getUniqueId().toString();
        final File file = new File("plugins/" + str + "/" + uuid + ".yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        final OldpCon pOldpCon = new OldpCon(p, str);
        return pOldpCon;
    }
    
    public boolean isSet(final String string) {
        return this.config.isSet(string);
    }
    
    public boolean getBoolean(final String string) {
        return this.config.getBoolean(string);
    }
    
    public int getWorldID() {
        return this.config.getInt("World.id");
    }
    
    public void setWorldID(final int newWorldID) {
        this.config.set("World.id", (Object)newWorldID);
        this.savePCon();
    }
    
    public void set(final String string, final Object arg1) {
        this.config.set(string, arg1);
        this.savePCon();
    }
    
    public String getString(final String string) {
        return this.config.getString(string);
    }
    
    public List<String> getStringList(final String string) {
        return (List<String>)this.config.getStringList(string);
    }
    
    public float getCoins() {
        return this.config.isSet("Player.Coins") ? ((float)this.config.getDouble("Player.Coins")) : 0.0f;
    }
    
    public float getFloat(final String string) {
        return (float)this.config.getDouble(string);
    }
    
    public int getInt(final String string) {
        return this.config.getInt(string);
    }
    
    public long getLong(final String string) {
        return this.config.getLong(string);
    }
    
    public double getDouble(final String string) {
        return this.config.getDouble(string);
    }
    
    public void setCoins(final float i) {
        this.config.set("Player.Coins", (Object)i);
        this.savePCon();
    }
    
    public void savePCon() {
        try {
            this.config.save(this.file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void savePConErrorFree() {
        try {
            this.config.save(this.file);
        }
        catch (IOException ex) {}
    }
}
