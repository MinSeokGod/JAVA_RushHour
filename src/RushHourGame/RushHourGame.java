package RushHourGame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class RushHourGame extends JFrame {
	private AudioManager backgroundMusic; // 배경음악
	private AudioManager mainClickSound; // click
	private AudioManager arrowClickSound; // arrow click

	// 스크린 화면 구성
	private Image screenImage;
	private Graphics screenGraphic;

	private Image background = new ImageIcon(Main.class.getResource("../images/Screen/introBackground.png")).getImage();

	// 이미지 아이콘 구성
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/Button/ExitBasic.png"));
	private ImageIcon exitButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/Button/ExitEntered.png"));
	private ImageIcon startButton6BasicImage = new ImageIcon(
			Main.class.getResource("../images/Button/Main/Start6.png"));
	private ImageIcon startButton6EnteredImage = new ImageIcon(
			Main.class.getResource("../images/Button/Main/Start6Entered.png"));
	private ImageIcon startButton12BasicImage = new ImageIcon(
			Main.class.getResource("../images/Button/Main/Start12.png"));
	private ImageIcon startButton12EnteredImage = new ImageIcon(
			Main.class.getResource("../images/Button/Main/Start12Entered.png"));
	private ImageIcon startButtonSBasicImage = new ImageIcon(
			Main.class.getResource("../images/Button/Main/StartS.png"));
	private ImageIcon startButtonSEnteredImage = new ImageIcon(
			Main.class.getResource("../images/Button/Main/StartSEntered.png"));
	private ImageIcon leftButtonSBasicImage = new ImageIcon(
			Main.class.getResource("../images/Button/Select/leftButton.png"));
	private ImageIcon leftButtonSEnteredImage = new ImageIcon(
			Main.class.getResource("../images/Button/Select/leftButtonEntered.png"));
	private ImageIcon rightButtonSBasicImage = new ImageIcon(
			Main.class.getResource("../images/Button/Select/rightButton.png"));
	private ImageIcon rightButtonSEnteredImage = new ImageIcon(
			Main.class.getResource("../images/Button/Select/rightButtonEntered.png"));

	// 바 선언
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/Button/menuBar.png")));

	// 버튼 선언 (마우스가 올라가기 전 기본 상태)
	private JButton startButton6x6 = new JButton(startButton6BasicImage);
	private JButton startButton12x12 = new JButton(startButton12BasicImage);
	private JButton startButtonShift = new JButton(startButtonSBasicImage);
	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton leftButton = new JButton(leftButtonSBasicImage);
	private JButton rightButton = new JButton(rightButtonSBasicImage);

	// 화면 판별용 변수
	private boolean isSelectScreen = false;

	// 맵을 선택화면의 변수들을 모아놓음.
	ArrayList<Track> trackList = new ArrayList<Track>(); // 각각의 맵을 담을 수 있는 리스트를 만든다.
	private Image titleImage;
	private Image selectedImage;
	private int nowSelected = 0; // 현재 맵은 0부터 시작함. 인덱스이므로 0부터 시작해야함.
	// Track 클래스의 생성자를 이용하여 맵 선택에서 Easy 맵과 Hard 맵을 만들고 이를 trackList에 저장함.
	
	// 마우스 위치
	private int mouseX, mouseY;

	public RushHourGame() {
		setUndecorated(true);
		setTitle("RushHourGame");
		setSize(Main.WIDTH, Main.HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		backgroundMusic = new AudioManager("src/Music/StartBGM.wav", true); // 오디오 매니저를 통하여 bgm 객체를 만들어서 bgm 재생시키기
		backgroundMusic.start();
		mainClickSound = new AudioManager("src/Music/mainClickButton.wav", false);
		arrowClickSound = new AudioManager("src/Music/mapSelectClickButton.wav", false);

		// 종료 버튼 추가
		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			// 마우스를 올려 놓음
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 커서 모양 바꿈
				// 추후 이곳에 효과음 추가하기
			}

			// 마우스를 올려 놓지 않음
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 커서 모양 바꿈
			}

			// 마우스를 클릭함
			public void mousePressed(MouseEvent e) {
				// 추후 이곳에 효과음 추가하기
				mainClickSound.start();
				System.exit(0);
			}
		});
		add(exitButton);

		// 6x6모드 시작 버튼 추가
		startButton6x6.setBounds(900, 100, 128, 128);
		startButton6x6.setBorderPainted(false);
		startButton6x6.setContentAreaFilled(false);
		startButton6x6.setFocusPainted(false);
		startButton6x6.addMouseListener(new MouseAdapter() {
			// 마우스를 올려 놓음
			public void mouseEntered(MouseEvent e) {
				startButton6x6.setIcon(startButton6EnteredImage);
				startButton6x6.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 커서 모양 바꿈
			}

			// 마우스를 올려 놓지 않음
			public void mouseExited(MouseEvent e) {
				startButton6x6.setIcon(startButton6BasicImage);
				startButton6x6.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 커서 모양 바꿈
			}

			// 마우스를 클릭함
			public void mousePressed(MouseEvent e) {
				mainClickSound.start();
				startButton6x6.setVisible(false);
				startButton12x12.setVisible(false);
				startButtonShift.setVisible(false);
				leftButton.setVisible(true);
				rightButton.setVisible(true);
				trackList.add(new Track("easyTitleImage.png", "easy6x6startmap.png", "easy6x6map.png"));
				trackList.add(new Track("hardTitleImage.png", "hard6x6startmap.png", "hard6x6map.png"));
				selectTrack(0);
				background = new ImageIcon(Main.class.getResource("../images/Screen/mainBackground.png")).getImage();
				isSelectScreen = true;
			}
		});
		add(startButton6x6);

		// 12x12 모드 시작 버튼 추가
		startButton12x12.setBounds(900, 250, 128, 128);
		startButton12x12.setBorderPainted(false);
		startButton12x12.setContentAreaFilled(false);
		startButton12x12.setFocusPainted(false);
		startButton12x12.addMouseListener(new MouseAdapter() {
			// 마우스를 올려 놓음
			public void mouseEntered(MouseEvent e) {
				startButton12x12.setIcon(startButton12EnteredImage);
				startButton12x12.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 커서 모양 바꿈
			}

			// 마우스를 올려 놓지 않음
			public void mouseExited(MouseEvent e) {
				startButton12x12.setIcon(startButton12BasicImage);
				startButton12x12.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 커서 모양 바꿈
			}

			// 마우스를 클릭함
			public void mousePressed(MouseEvent e) {
				mainClickSound.start();
				startButton6x6.setVisible(false);
				startButton12x12.setVisible(false);
				startButtonShift.setVisible(false);
				leftButton.setVisible(true);
				rightButton.setVisible(true);
				trackList.add(new Track("easyTitleImage.png", "easy12x12startmap.png", "easy12x12map.png"));
				trackList.add(new Track("hardTitleImage.png", "hard12x12startmap.png", "hard12x12map.png"));
				selectTrack(0);
				background = new ImageIcon(Main.class.getResource("../images/Screen/mainBackground.png")).getImage();
				isSelectScreen = true;
			}
		});
		add(startButton12x12);

		// Shift 모드 시작 버튼 추가
		startButtonShift.setBounds(900, 400, 128, 128);
		startButtonShift.setBorderPainted(false);
		startButtonShift.setContentAreaFilled(false);
		startButtonShift.setFocusPainted(false);
		startButtonShift.addMouseListener(new MouseAdapter() {
			// 마우스를 올려 놓음
			public void mouseEntered(MouseEvent e) {
				startButtonShift.setIcon(startButtonSEnteredImage);
				startButtonShift.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 커서 모양 바꿈
			}

			// 마우스를 올려 놓지 않음
			public void mouseExited(MouseEvent e) {
				startButtonShift.setIcon(startButtonSBasicImage);
				startButtonShift.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 커서 모양 바꿈
			}

			// 마우스를 클릭함
			public void mousePressed(MouseEvent e) {
				mainClickSound.start();
				startButton6x6.setVisible(false);
				startButton12x12.setVisible(false);
				startButtonShift.setVisible(false);
				selectTrack(0);
				leftButton.setVisible(true);
				rightButton.setVisible(true);
				background = new ImageIcon(Main.class.getResource("../images/Screen/mainBackground.jpg")).getImage();
				isSelectScreen = true;
			}
		});
		add(startButtonShift);

		// 왼쪽 화살표 추가
		leftButton.setVisible(false);
		leftButton.setBounds(140, 310, 60, 60);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			// 마우스를 올려 놓음
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonSEnteredImage);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 커서 모양 바꿈
				// 추후 이곳에 효과음 추가하기
			}

			// 마우스를 올려 놓지 않음
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonSBasicImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 커서 모양 바꿈
			}

			// 마우스를 클릭함
			public void mousePressed(MouseEvent e) {
				arrowClickSound.start();
				selectLeft(); // 왼쪽 화살표를 누르는 동작
			}
		});
		add(leftButton);

		// 오른쪽 화살표 추가
		rightButton.setVisible(false);
		rightButton.setBounds(1080, 310, 60, 60);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			// 마우스를 올려 놓음
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonSEnteredImage);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 커서 모양 바꿈
				// 추후 이곳에 효과음 추가하기
			}

			// 마우스를 올려 놓지 않음
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonSBasicImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 커서 모양 바꿈
			}

			// 마우스를 클릭함
			public void mousePressed(MouseEvent e) {
				arrowClickSound.start();
				selectRight(); // 오른쪽 화살표를 누르는 동작
			}
		});
		add(rightButton);

		// 메뉴바 추가
		menuBar.setBounds(0, 0, 1280, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY(); // 마우스 클릭 이벤트가 발생했을때의 x좌표와 y좌표를 얻어옴.
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {// 마우스의 움직임 리스너
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY); // 메뉴바를 마우스 드래그 하여 창을 이동 시킬 수 있음.
			}
		});
		add(menuBar);
	}

	public void paint(Graphics g) { // 버퍼에 메모리상에서 그림을 그린 후 screenImage에 저장하여 출력하는 형태.
		screenImage = createImage(Main.WIDTH, Main.HEIGHT); // width와 height 만큼의 공간을 만들고 (도화지)
		screenGraphic = screenImage.getGraphics(); // screenImage에 그래픽 객체(붓)를 추가함. 이를 draw 함수에 넘김
		screenDraw(screenGraphic); // 그래픽 객체와 screenImage가 저장된 screenGraphic를 screenDraw 함수에 넘김.
		g.drawImage(screenImage, 0, 0, null); // 최종적으로 화면g에 screenImage를 그려줌.
	}

	public void screenDraw(Graphics g) { // 메모리상에서 screen을 그림
		g.drawImage(background, 0, 0, null); // background 이미지가 screenGraphics (g)를 통해 screenImage에 그려짐
		if (isSelectScreen) {
			g.drawImage(selectedImage, 340, 100, null); // add 같은게 아니라 단순한 이미지를 출력 하는 것임.
			g.drawImage(titleImage, 340, 70, null);
		}
		paintComponents(g); // 이미지가 아닌 메인프레임에 추가 된 요소를 따로 그려줌 예를들어) add(버튼) 등
		this.repaint(); // paint 함수를 다시 불러옴.
	}

	public void selectTrack(int nowSelected) {
		titleImage = new ImageIcon(
				Main.class.getResource("../images/Screen/SelectMap/" + trackList.get(nowSelected).getTitleImage()))
				.getImage(); // 맵의 제목을 바꿔줌. (Easy, Hard)
		selectedImage = new ImageIcon(
				Main.class.getResource("../images/Screen/SelectMap/" + trackList.get(nowSelected).getStartImage()))
				.getImage(); // 맵의 이미지를 바꿔줌.
	}

	public void selectLeft() {
		if (nowSelected == 0) {
			nowSelected = trackList.size() - 1;
		} else
			nowSelected--;
		selectTrack(nowSelected);

	}

	public void selectRight() {
		if (nowSelected == trackList.size() - 1) {
			nowSelected = 0;
		} else
			nowSelected++;
		selectTrack(nowSelected);
	}

}