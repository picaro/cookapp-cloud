package com.op.cookcloud.helper.dbupdater;

import lombok.Data;

public @Data
class Version implements Comparable<Version> {

    private static final int BEFORE = -1;
    private static final int EQUAL = 0;
    private static final int AFTER = 1;

    private Integer major;

    private Integer minor;

    public Version() {
    }

    public Version(String version) {
        Integer major = Integer.parseInt(version.split("[.]")[0]);
        Integer minor = 0;
        if (version.split("[.]").length > 1) {
            minor = Integer.parseInt(version.split("[.]")[1]);
        }
        setMajor(major);
        setMinor(minor);
    }


    @Override
    public int compareTo(Version version) {
        if (this == version) {
            return EQUAL;
        }
        if (this.getMajor().equals(version.getMajor()) && this.getMinor().equals(version.getMinor())) {
            return EQUAL;
        }
        if (this.getMajor() > version.getMajor()) {
            return AFTER;
        }
        if (this.getMajor().equals(version.getMajor()) && this.getMinor() > version.getMinor()) {
            return AFTER;
        }
        return BEFORE;
    }

    public boolean isLessThan(Version otherVersion) {
        return compareTo(otherVersion) < 0;
    }




}
