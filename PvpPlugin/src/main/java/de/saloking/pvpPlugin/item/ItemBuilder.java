package de.saloking.pvpPlugin.item;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class ItemBuilder {

    private final ItemStack itemStack;
    private ItemMeta itemMeta;

    public ItemBuilder(Material material) {
        this.itemStack = new ItemStack(material);
    }

    public ItemBuilder setAmount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder setDisplayName(Component name) {
        this.itemMeta = this.itemStack.getItemMeta();
        this.itemMeta.displayName(name);
        this.itemStack.setItemMeta(this.itemMeta);
        return this;
    }

    public ItemBuilder setDisplayName(String name) {
        return setDisplayName(Component.text(name));
    }

    public ItemBuilder setLore(Component... lore) {
        this.itemMeta = this.itemStack.getItemMeta();

        this.itemMeta.lore(
                Arrays.stream(lore)
                        .collect(ArrayList::new, ArrayList::add, ArrayList::addAll)
        );

        this.itemStack.setItemMeta(this.itemMeta);
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        return setLore(
                Arrays.stream(lore)
                        .map(Component::text)
                        .toArray(Component[]::new)
        );
    }

    public ItemBuilder setLore(ArrayList<String> lore) {
        return setLore(
                lore.toArray(new String[0])
        );
    }

    public ItemBuilder setDurability(Short s) {
        this.itemMeta = this.itemStack.getItemMeta();

        if (!(this.itemMeta instanceof Damageable damageable))
            return this;

        damageable.setDamage(s);

        this.itemStack.setItemMeta(this.itemMeta);

        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int value) {
        this.itemMeta = this.itemStack.getItemMeta();
        this.itemMeta.addEnchant(enchantment, value, true);
        this.itemStack.setItemMeta(this.itemMeta);
        return this;
    }

    public ItemBuilder addEnchantmentGlow() {
        this.itemMeta = this.itemStack.getItemMeta();
        this.itemMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
        this.itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        this.itemStack.setItemMeta(this.itemMeta);
        return this;
    }

    public ItemBuilder setEnchantmentGlow(boolean glow) {
        if (!glow) {
            this.itemMeta = this.itemStack.getItemMeta();
            this.itemMeta.removeEnchant(Enchantment.UNBREAKING);
            this.itemStack.setItemMeta(this.itemMeta);
            return this;
        }

        this.itemMeta = this.itemStack.getItemMeta();
        this.itemMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
        this.itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        this.itemStack.setItemMeta(this.itemMeta);
        return this;
    }

    public ItemBuilder addItemFlags(ItemFlag... flags) {
        this.itemMeta = this.itemStack.getItemMeta();
        this.itemMeta.addItemFlags(flags);
        this.itemStack.setItemMeta(this.itemMeta);
        return this;
    }

    public ItemStack build() {
        return itemStack;
    }
}