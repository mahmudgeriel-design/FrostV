package com.frostvisuals.features.hud;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Score;
import java.util.*;

public class ScoreboardHud extends HudElement {
    public ScoreboardHud() { super("Scoreboard", 0.80f, 0.40f); }

    @Override
    public void render(MatrixStack ms, float pt) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null) return;
        Scoreboard sb = mc.level.getScoreboard();
        ScoreObjective obj = sb.getDisplayObjective(1);
        if (obj == null) { width = 0; height = 0; return; }
        Collection<Score> scores = sb.getPlayerScores(obj);
        int x = getRenderX(), y = getRenderY();
        width = 0; height = 0;
        String title = obj.getDisplayName().getString();
        drawText(ms, title, x, y + height);
        height += font().lineHeight + 2;
        width = Math.max(width, font().width(title));
        int shown = 0;
        List<Score> sorted = new ArrayList<>(scores);
        sorted.sort((a, b) -> b.getScore() - a.getScore());
        for (Score sc : sorted) {
            if (shown++ >= 15) break;
            String line = sc.getOwner() + ": " + sc.getScore();
            drawText(ms, line, x, y + height);
            width = Math.max(width, font().width(line));
            height += font().lineHeight + 1;
        }
    }
}
