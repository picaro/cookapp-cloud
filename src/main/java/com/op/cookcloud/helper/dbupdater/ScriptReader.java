package com.op.cookcloud.helper.dbupdater;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.springframework.core.io.ClassPathResource;

public class ScriptReader {

    private static final SortedMap<Version, File> EMPTY_SORTED_MAP = new TreeMap<Version, File>();

    public SortedMap<Version, File> loadAndSortUpdateScriptFilesByVersion(String updateScriptsDirectoryPath) {
        try {
            File deltasDir = new ClassPathResource(updateScriptsDirectoryPath).getFile();
            if (deltasDir.exists() && deltasDir.isDirectory()) {
                return fillDeltasMap(deltasDir);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return EMPTY_SORTED_MAP;
    }

    /**
     *
     * @param deltasDir
     * @return
     */
    private SortedMap<Version, File> fillDeltasMap(final File deltasDir) {
        Iterator<File> iterator = FileUtils.iterateFiles(deltasDir, new RegexFileFilter("dbupdate[_]\\d+[.]\\d+[.]sql"), null);
        SortedMap<Version, File> map = new TreeMap<Version, File>();
        while (iterator.hasNext()) {
            File delta = iterator.next();
            Version version = new Version(delta.getName().split("_")[1].replace(".sql", ""));
            map.put(version, delta);
        }
        return map;
    }

    /**
     *
     * @param file
     * @return
     * @throws IOException
     */
    public String readDbUpdateScriptFromFile(final File file) throws IOException {
        StringBuffer buffer = new StringBuffer();
        LineIterator iterator = FileUtils.lineIterator(file);
        while (iterator.hasNext()) {
            buffer.append(iterator.next());
            buffer.append(System.getProperty("line.separator"));
        }
        return buffer.toString();
    }

}
