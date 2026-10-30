package de.johnpork.companion;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public final class JohnPorkScreen extends Screen {
    public JohnPorkScreen() {
        super(Component.literal("JohnPork – Begleiter-Menü"));
    }

    @Override
    protected void init() {
        int cx = this.width / 2;
        int y = this.height / 4;

        addRenderableWidget(Button.builder(Component.literal("Folgen (Prototyp)"),
                b -> action("Folgen ausgewählt")).bounds(cx - 100, y, 200, 20).build());
        y += 25;
        addRenderableWidget(Button.builder(Component.literal("Warten (Prototyp)"),
                b -> action("Warten ausgewählt")).bounds(cx - 100, y, 200, 20).build());
        y += 25;
        addRenderableWidget(Button.builder(Component.literal("Hinsetzen (Prototyp)"),
                b -> action("Hinsetzen ausgewählt")).bounds(cx - 100, y, 200, 20).build());
        y += 25;
        addRenderableWidget(Button.builder(Component.literal("Tanzen (Prototyp)"),
                b -> action("Tanzen ausgewählt")).bounds(cx - 100, y, 200, 20).build());
        y += 25;
        addRenderableWidget(Button.builder(Component.literal("Um Essen bitten (Prototyp)"),
                b -> action("Um Essen bitten ausgewählt")).bounds(cx - 100, y, 200, 20).build());
        y += 32;
        addRenderableableCloseButton(cx, y);
    }

    private void addRenderableableCloseButton(int cx, int y) {
        addRenderableWidget(Button.builder(Component.literal("Schließen"),
                b -> onClose()).bounds(cx - 100, y, 200, 20).build());
    }

    private void action(String message) {
        JohnPorkClient.announce(message + " – NPC-Steuerung folgt in einer späteren Version.");
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        renderBackground(graphics, mouseX, mouseY, delta);
        super.render(graphics, mouseX, mouseY, delta);
        graphics.drawCenteredString(font, title, width / 2, height / 4 - 24, 0xFFFFB6D9);
        graphics.drawCenteredString(font,
                Component.literal("Früher Prototyp – noch ohne sichtbare Begleiterin"),
                width / 2, height - 35, 0xFFBBBBBB);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
