package maqetta.server.orion;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.util.ArrayList;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.orion.server.core.users.OrionScope;
import org.maqetta.server.IDavinciServerConstants;
import org.maqetta.server.IStorage;
import org.maqetta.server.IVResource;
import org.maqetta.server.VFile;

public class VOrionResource extends VFile {

	IFileStore store;
	protected static final IScopeContext scope = new OrionScope();
	protected IEclipsePreferences prefStore;

	
	
	public VOrionResource(IStorage storage,  IVResource parent, String name) {
		super(storage,parent);
		this.virtualPath = name;
	}

    protected IStorage getWorkingCopy(IStorage original) {
    	
    	if(this.file.isDirectory()){
    		// no working copy for directories (java.io.file handled this before 
    		return this.file;
    	}
    	
    	IStorage parent = original.getParentFile();
    	IStorage workingCopy;
    	if(parent!=null)
    		workingCopy = original.newInstance(parent, original.getName() + IDavinciServerConstants.WORKING_COPY_EXTENSION);
    	else
    		workingCopy = original.newInstance(original.getName() + IDavinciServerConstants.WORKING_COPY_EXTENSION);
    	return workingCopy;
    }
    
    public IStorage getStorage(){
    	return this.file;
    }
}
