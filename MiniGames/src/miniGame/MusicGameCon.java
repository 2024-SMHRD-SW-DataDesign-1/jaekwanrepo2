package miniGame;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javazoom.jl.player.MP3Player;

public class MusicGameCon extends MP3Player {
	Scanner sc = new Scanner(System.in);
	image im = new image();
	Random ran = new Random();
	PathDAO pathdao = new PathDAO();
	RankDAO rank = new RankDAO();

	public void playTest(String id) {

		ArrayList<Integer> ranSong = ranNum(pathdao.songData().size());
		int sum = 0;
		for (int j = 0; j < 5; j++) {
			int score = 60;
			for (int i = 1; i <= 3; i++) {
				im.printImage();
				play(pathdao.songData().get(ranSong.get(j)).getPath());
				timeDelay(i * 5);
				stop();

				System.out.print("정답(한글로작성)" + score + "점 : ");
				if (pathdao.songData().get(ranSong.get(j)).getName().equals(sc.next())) {
					System.out.println("정답입니다.");
					timeDelay(2);
					break;
				} else {
					System.out.println("오답입니다.");
					score -= 20;
					timeDelay(2);
				}

			}
			sum += score;
			System.out.println(sum);
		}
		
		
		rank(1,id,sum);

	}

	public ArrayList<Integer> ranNum(int max) {
		ArrayList<Integer> result = new ArrayList<Integer>();

		for (int i = 0; i < max; i++) {
			int randomNumber = ran.nextInt(max);

			while (result.contains(randomNumber)) {
				randomNumber = ran.nextInt(max);
			}
			result.add(randomNumber);
		}

		return result;
	}

	public void timeDelay(int i) {
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	public void rank(int num,String id,int sum) {
		RankDAO rank = new RankDAO();
		//랭크 입력
		rank.rank(num, id, sum);
		
		//랭크 출력
		ArrayList<RankDTO> result = rank.searchRank(num);
		if (num == 1) {
			for (RankDTO i : result) {
				System.out.println(i.getId() + "\t" + i.getMusicScore());
			}
		}
		else if (num == 2) {
			for (RankDTO i : result) {
				System.out.println(i.getId() + "\t" + i.getCodeScore());
			}
		}
		else if (num == 3) {
			for (RankDTO i : result) {
				System.out.println(i.getId() + "\t" + i.getImageScore());
			}
		}
		else if (num == 4) {
			for (RankDTO i : result) {
				System.out.println(i.getId() + "\t" + i.getWordScore());
			}
		}
		
		
	}
	

}
