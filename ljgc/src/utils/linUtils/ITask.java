package utils.linUtils;

public interface ITask extends Runnable {
	abstract void stop();

	@Override
	abstract void run();
}
