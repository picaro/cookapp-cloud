package com.op.cookcloud.helper.dbupdater;

import java.io.File;
import java.util.SortedMap;

import com.op.cookcloud.model.base.Version;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class DbUpdater {

    protected final Log logger = LogFactory.getLog(getClass());
    
	private static final String DB_UPDATE_SCRIPT_DIRECTORY = "dbupdate";

	private String updateScriptsDirectoryPath = DB_UPDATE_SCRIPT_DIRECTORY;
    @Autowired
	private ScriptReader scriptReader;
	@Autowired
	private Executor executor;

	public void setUpdateScriptsDirectoryPath(String path) {
		updateScriptsDirectoryPath = path;
	}

	public void updateDb() {
		synchronized (DbUpdater.class) {
			SortedMap<Version, File> updateScriptFilesWithVersions = scriptReader.loadAndSortUpdateScriptFilesByVersion(updateScriptsDirectoryPath);
			executor.executeUpdateScriptFiles(updateScriptFilesWithVersions);
			logger.info("All update scripts executed succesfully " + updateScriptFilesWithVersions.values());
		}
	}

}
