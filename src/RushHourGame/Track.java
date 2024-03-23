package RushHourGame;

public class Track { //아래 변수들의 이름은 좀 더 가독성 있게 바꾸기 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	private String titleImage; //제목 부분 이미지
	private String startImage; //맵 선택 이미지
	private String gameImage; //실제 맵 이미지
	
	public String getTitleImage() {
		return titleImage;
	}
	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}
	public String getStartImage() {
		return startImage;
	}
	public void setStartImage(String startImage) {
		this.startImage = startImage;
	}
	public String getGameImage() {
		return gameImage;
	}
	public void setGameImage(String gameImage) {
		this.gameImage = gameImage;
	}
	
	//생성자
	public Track(String titleImage, String startImage, String gameImage) {
		super();
		this.titleImage = titleImage; //맵 미리보기 제목
		this.startImage = startImage; //맵 미리보기
		this.gameImage = gameImage; //실제 맵
	}
}
