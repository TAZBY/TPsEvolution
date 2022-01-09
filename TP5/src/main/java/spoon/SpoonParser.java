package spoon;

import javax.swing.text.html.HTMLEditorKit.Parser;

import spoon.Launcher;

public class SpoonParser {
	
	Launcher launcher ;
	
	public void setLauncher(String projectSrcPath ,String projectClassPath,String sourceOutputPath ) {
		launcher  = new Launcher();
		launcher.addInputResource(projectSrcPath); // set project source path
		launcher.getEnvironment().setSourceClasspath(new String[] {projectClassPath}); // set project classpath
		launcher.setSourceOutputDirectory(sourceOutputPath); // set generated source code directory path
		launcher.setBinaryOutputDirectory(sourceOutputPath+"/bin"); // set generated binary code directory path
		launcher.getEnvironment().setAutoImports(true); // set auto imports
		launcher.getEnvironment().setCommentEnabled(false); // set comments enabled
		launcher.getEnvironment().setShouldCompile(true);
		
	}

	public Launcher getLauncher() {
		return launcher;
	}

	public void setLauncher(Launcher launcher) {
		this.launcher = launcher;
	}
	
	
}
