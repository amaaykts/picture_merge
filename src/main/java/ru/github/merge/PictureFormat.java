package ru.github.merge;

public enum PictureFormat {
    PNG("png"), JPG("jpg");

    private String name;

    PictureFormat(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
