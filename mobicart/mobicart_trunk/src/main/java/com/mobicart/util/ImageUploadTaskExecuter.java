package com.mobicart.util;

import java.util.List;


import org.springframework.core.task.TaskExecutor;
 


public class ImageUploadTaskExecuter {

	private class ImageUploadTask implements Runnable {

	    private  MagicalPower magicalPower;

	    public ImageUploadTask(MagicalPower magicalPower) {
	      this.magicalPower = magicalPower;
	    }

	    public void run() {
	    	
	    	magicalPower.resizeAndSave();
	    }

	  }

	  private TaskExecutor taskExecutor;

	  
	  public ImageUploadTaskExecuter(TaskExecutor taskExecutor) {
	    this.taskExecutor = taskExecutor;
	  }

	  public void uploadImages(List<MagicalPower> magicalPowerItems) {
		  for(MagicalPower magicalPower:magicalPowerItems) {
	    	taskExecutor.execute(new ImageUploadTask(magicalPower));
		  }
	  }
	
}
