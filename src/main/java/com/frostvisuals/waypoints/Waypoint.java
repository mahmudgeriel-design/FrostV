package com.frostvisuals.waypoints;

import net.minecraft.util.math.BlockPos;

public class Waypoint {
    private String name;
    private BlockPos pos;
    private int color;
    private String dimension;

    public Waypoint(String name, BlockPos pos, int color, String dimension) {
        this.name = name; this.pos = pos; this.color = color; this.dimension = dimension;
    }

    public String getName() { return name; }
    public BlockPos getPos() { return pos; }
    public int getColor() { return color; }
    public String getDimension() { return dimension; }
    public void setName(String name) { this.name = name; }
    public void setColor(int color) { this.color = color; }

    public double distanceTo(double x, double y, double z) {
        return Math.sqrt(Math.pow(pos.getX()-x,2)+Math.pow(pos.getY()-y,2)+Math.pow(pos.getZ()-z,2));
    }
}
