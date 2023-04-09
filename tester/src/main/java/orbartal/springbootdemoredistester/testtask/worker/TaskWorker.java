package orbartal.springbootdemoredistester.testtask.worker;

import orbartal.springbootdemoredistester.task.model.TaskReport;

public interface TaskWorker <R extends TaskReport> {

	public R work();

}
