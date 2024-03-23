package RushHourGame;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class AudioManager {
	private Clip clip;
	private File audioFile;
	private AudioInputStream audioInputStream;
	private boolean isLoop;

	public AudioManager(String pathName, boolean isLoop) {
		try {
			clip = AudioSystem.getClip();
			audioFile = new File(pathName);
			audioInputStream = AudioSystem.getAudioInputStream(audioFile);
			clip.open(audioInputStream);

			// 소리 설정
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			// 불륨 조절
			gainControl.setValue(-20.0f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void start() {
		clip.setFramePosition(0);
		clip.start();
		if (isLoop)
			clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void stop() {
		clip.stop();
	}

	public void playMainButtonClickSound(String soundFilePath) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("src/Music/mainClickButton.wav"));
			Clip buttonClickClip = AudioSystem.getClip();
			buttonClickClip.open(ais);
			buttonClickClip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}