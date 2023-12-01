package com.hostmdy.movie.bootstrap;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.hostmdy.movie.domain.Comment;
import com.hostmdy.movie.domain.Episodes;
import com.hostmdy.movie.domain.Generes;
import com.hostmdy.movie.domain.Movie;
import com.hostmdy.movie.domain.Photos;
import com.hostmdy.movie.domain.Quality;
import com.hostmdy.movie.domain.ReleaseYear;
import com.hostmdy.movie.domain.Synopsis;
import com.hostmdy.movie.domain.Type;
import com.hostmdy.movie.domain.Viewer;
import com.hostmdy.movie.repository.CommentRepository;
import com.hostmdy.movie.repository.GeneresRepository;
import com.hostmdy.movie.repository.MovieRepository;
import com.hostmdy.movie.repository.ReleaseYearRepository;
import com.hostmdy.movie.repository.ViewerRepository;

@Component
public class MovieBootstrap implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired
	private GeneresRepository generesRepository;
	@Autowired
	private ReleaseYearRepository releaseYearRepository;
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private ViewerRepository viewerRepository;
	@Autowired
	private CommentRepository commentRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		movieRepository.saveAll(dataBootstrap());
		

		Optional<Viewer> mgmgOpt = viewerRepository.findByName("Mg Mg");
		Optional<Viewer> masapalOpt = viewerRepository.findByName("MaSapal");
		Movie missionImpossible = movieRepository.findByTitle("Mission: Impossible-Dead Reckoning");
		Comment missionCommentByMgMg = new Comment();
		missionCommentByMgMg.setComment("This Movie is so cool. I am Tom Cruise fan.");
		missionCommentByMgMg.setRecord(LocalDate.of(2023, Month.AUGUST, 12));
		missionCommentByMgMg.setRecordTime(LocalTime.of(01, 15, 45));
		missionCommentByMgMg.setMovie(missionImpossible);
		missionCommentByMgMg.setWriter("Mg Mg");
		missionCommentByMgMg.setViewer(mgmgOpt.get());
		commentRepository.save(missionCommentByMgMg);
		
		Comment missionCommentByMaSapal = new Comment();
		missionCommentByMaSapal.setComment("How are you. I hope you are well. I am fine.");
		missionCommentByMaSapal.setRecord(LocalDate.of(2023, Month.SEPTEMBER, 29));
		missionCommentByMaSapal.setRecordTime(LocalTime.of(01, 15, 45));
		missionCommentByMaSapal.setMovie(missionImpossible);
		missionCommentByMaSapal.setWriter("MaSapal");
		missionCommentByMaSapal.setViewer(masapalOpt.get());
		commentRepository.save(missionCommentByMaSapal);
		
		Comment missionCommentByMgMg1 = new Comment();
		missionCommentByMgMg1.setComment("I am fine. Thank you.");
		missionCommentByMgMg1.setRecord(LocalDate.of(2023, Month.OCTOBER, 22));
		missionCommentByMgMg1.setRecordTime(LocalTime.of(01, 15, 45));
		missionCommentByMgMg1.setMovie(missionImpossible);
		missionCommentByMgMg1.setWriter("Mg Mg");
		missionCommentByMgMg1.setViewer(mgmgOpt.get());
		commentRepository.save(missionCommentByMgMg1);
		
		Movie lokiS1 = movieRepository.findByTitle("Loki Season1");
		Comment lokiS1CommentByMgMg = new Comment();
		lokiS1CommentByMgMg.setComment("I'm also fan of Tom Hiddleston. ");
		lokiS1CommentByMgMg.setRecord(LocalDate.of(2022, Month.SEPTEMBER, 28));
		lokiS1CommentByMgMg.setRecordTime(LocalTime.of(01, 15, 45));
		lokiS1CommentByMgMg.setMovie(lokiS1);
		lokiS1CommentByMgMg.setWriter("Mg Mg");
		lokiS1CommentByMgMg.setViewer(mgmgOpt.get());
		commentRepository.save(lokiS1CommentByMgMg);
		
		Movie lokiS2 = movieRepository.findByTitle("Loki Season2");
		Comment lokiS2CommentByMaSapal = new Comment();
		lokiS2CommentByMaSapal.setComment("Wow The last ep was Amazing... ");
		lokiS2CommentByMaSapal.setRecord(LocalDate.of(2023, Month.NOVEMBER, 5));
		lokiS2CommentByMaSapal.setRecordTime(LocalTime.of(01, 15, 45));
		lokiS2CommentByMaSapal.setMovie(lokiS2);
		lokiS2CommentByMaSapal.setWriter("Mg Mg");
		lokiS2CommentByMaSapal.setViewer(masapalOpt.get());
		commentRepository.save(lokiS2CommentByMaSapal);
	}
	
	private byte[] changeFileToBytes(String filePath) {
		byte[] imageBytes = null;
		try {
			FileInputStream fileInputStream = new FileInputStream(new File(filePath));
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[4000];
			while (bufferedInputStream.read(buffer, 0, buffer.length) != -1) {
				byteArrayOutputStream.write(buffer, 0, buffer.length);
			}
			imageBytes = byteArrayOutputStream.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return imageBytes;
	}
	
	private List<Movie> dataBootstrap(){
		Viewer viewer = new Viewer();
		viewer.setName("Admin");
		viewer.setEmail("admin@gmail.com");
		viewer.setRole("admin");
		viewer.setPassword("1000:4378ce9a0ecb21bfda36acc98979a044:90fec8d4076873507af5a2a2f9eec0324ab5be62534f8474159eebb52cb46baca6f81be8b230e237f5650ad4986ab2fd14fe0e962384425e0bcd0d2bea29960c");//1234
		viewerRepository.save(viewer);
		
		Viewer mgmg = new Viewer();
		mgmg.setName("Mg Mg");
		mgmg.setEmail("mgmg@gmail.com");
		mgmg.setRole("user");
		mgmg.setPassword("1000:db9f694910b194cb300b7b597886fd39:3c7478ba52cc42e486e082907b11b7d8e4a1f4820643b08d872667819bfa31ab3ecea58607a5d2d97f7b5d09c27893d216729b13ec713c2f847547043ec56220");//mgmg1234
		viewerRepository.save(mgmg);
		
		Viewer masapal = new Viewer();
		masapal.setName("MaSapal");
		masapal.setEmail("masapal@gmail.com");
		masapal.setRole("user");
		masapal.setPassword("1000:ad9414e80af82b597d9dcf15dbff85a2:7adb8b5b14bea77754a3151271e6eb0096a3cbf6105508f0c85426630f8b82dd9b083e3947d9889cf90abd2972d6842805e9337d6a55ea178fdcc2dca4e990f9");//masapal1234
		viewerRepository.save(masapal);
		
		List<Movie> movies = new ArrayList<>();
		
		Generes fantasy = new Generes();
		fantasy.setGenere("Fantasy");
		Generes horror = new Generes();
		horror.setGenere("Horror");
		Generes mystery = new Generes();
		mystery.setGenere("Mystery");
		Generes action = new Generes();
		action.setGenere("Action");
		Generes thriller = new Generes();
		thriller.setGenere("Thriller");
		Generes drama = new Generes();
		drama.setGenere("Drama");
		Generes adventure = new Generes();
		adventure.setGenere("Adventure");
		Generes scienceFiction = new Generes();
		scienceFiction.setGenere("Science Fiction");
		Generes history = new Generes();
		history.setGenere("History");

		ReleaseYear year2018 = new ReleaseYear();
		year2018.setReleasedYear(2018);
		ReleaseYear year2019 = new ReleaseYear();
		year2019.setReleasedYear(2019);
		ReleaseYear year2020 = new ReleaseYear();
		year2020.setReleasedYear(2020);
		ReleaseYear year2021 = new ReleaseYear();
		year2021.setReleasedYear(2021);
		ReleaseYear year2022 = new ReleaseYear();
		year2022.setReleasedYear(2022);
		ReleaseYear year2023 = new ReleaseYear();
		year2023.setReleasedYear(2023);
		
		
		generesRepository.save(fantasy);
		generesRepository.save(horror);
		generesRepository.save(mystery);
		generesRepository.save(action);
		generesRepository.save(thriller);
		generesRepository.save(drama);
		generesRepository.save(adventure);
		generesRepository.save(scienceFiction);
		generesRepository.save(history);

		releaseYearRepository.save(year2018);
		releaseYearRepository.save(year2019);
		releaseYearRepository.save(year2020);
		releaseYearRepository.save(year2021);
		releaseYearRepository.save(year2020);
		releaseYearRepository.save(year2022);
		releaseYearRepository.save(year2023);
		
		Movie missionImpossible = new Movie();
		missionImpossible.setTitle("Mission: Impossible-Dead Reckoning");
		missionImpossible.setCountry("UnitedStates");
		missionImpossible.setCreatedBy("Christopher McQuarrie");
		missionImpossible.setStarring("Tom Cruise,  Hayley Atwell,  Ving Rhames,  Simon Pegg,  Rebecca Ferguson,  Vanessa Kirby,  Esai Morales,  Pom Klementieff,  Henry Czerny,  Shea Whigham,  Greg Tarzan Davis,  Frederick Schmidt,  Mariela Garriga,  Cary Elwes,  Charles Parnell,  Mark Gatiss,  Indira Varma,  Rob Delaney,  Marcello Walton,  Brian Law,  Lincoln Conway,");
		missionImpossible.setType(Type.MOVIE);
		missionImpossible.setRating(7.8);
		missionImpossible.setRuntime(164);
		missionImpossible.setReleaseDate(LocalDate.of(2023, 7, 12));
		missionImpossible.setView(400);
		missionImpossible.getGeneres().add(action);
		missionImpossible.getGeneres().add(thriller);
		
		action.getMovies().add(missionImpossible);
		thriller.getMovies().add(missionImpossible);
		
		missionImpossible.setReleaseYear(year2023);
		
		Synopsis synopsisForMissionImpo = new Synopsis();
		synopsisForMissionImpo.setSynopsisEng("Ethan Hunt and the IMF team must track down a terrifying new weapon that threatens all of humanity if it falls into the wrong hands. With control of the future and the fate of the world at stake, a deadly race around the globe begins. Confronted by a mysterious, all-powerful enemy, Ethan is forced to consider that nothing can matter more than the mission -- not even the lives of those he cares about most.");
		synopsisForMissionImpo.setSynopsisMm("Ethan Hunt နှင့် IMF အဖွဲ့သည် လူလက်ထဲရောက်သွားပါက လူသားအားလုံးကို ခြိမ်းခြောက်နိုင်သည့် ကြောက်မက်ဖွယ်ကောင်းသော လက်နက်သစ်ကို ခြေရာခံရမည်ဖြစ်သည်။ အနာဂတ်ကို ထိန်းချုပ်ထားပြီး ကမ္ဘာ့ကံကြမ္မာကို လောင်းကြေးထပ်ထားခြင်းဖြင့်၊ ကမ္ဘာတစ်ဝှမ်းတွင် သေစေနိုင်သော ပြိုင်ဆိုင်မှုတစ်ခု စတင်သည်။ လျှို့ဝှက်ဆန်းကြယ်ပြီး အစွမ်းထက်သော ရန်သူတစ်ဦးနှင့် ရင်ဆိုင်ရသောအခါတွင် Ethan သည် မစ်ရှင်ထက် မည်သည့်အရာမှ အရေးမကြီးနိုင်— သူသည် အလွန်ဂရုစိုက်သူများ၏ ဘဝပင်မဟုတ်ကြောင်း စဉ်းစားရတော့မည်ဖြစ်သည်။");
		
		missionImpossible.setSynopsis(synopsisForMissionImpo);
		
		missionImpossible.setImage(changeFileToBytes("src/main/resources/templates/images/Mission Impossible(2023).jpg"));
		
		List<Episodes> missionImpossibleEp = new ArrayList<>();
		Episodes ep1ForMissionImpossible = new Episodes();
		ep1ForMissionImpossible.setEpisode(1);
		ep1ForMissionImpossible.setLink("MissionImposible link");
		ep1ForMissionImpossible.setQuality(Quality._730P);
		ep1ForMissionImpossible.setMovie(missionImpossible);
		missionImpossibleEp.add(ep1ForMissionImpossible);
		
		missionImpossible.setEpisodes(missionImpossibleEp);
		
		List<Photos> missionImpossiblePhotos = new ArrayList<>();
		Photos missionImpossiblePhoto3 = new Photos();
		missionImpossiblePhoto3.setMovie(missionImpossible);
		missionImpossiblePhoto3.setPhoto(changeFileToBytes("src/main/resources/templates/images/MissionImpossible Scense3.jpg"));
		missionImpossiblePhotos.add(missionImpossiblePhoto3);
		Photos missionImpossiblePhoto4 = new Photos();
		missionImpossiblePhoto4.setMovie(missionImpossible);
		missionImpossiblePhoto4.setPhoto(changeFileToBytes("src/main/resources/templates/images/MissionImpossible Scense4.jpg"));
		missionImpossiblePhotos.add(missionImpossiblePhoto4);
		Photos missionImpossiblePhoto5 = new Photos();
		missionImpossiblePhoto5.setMovie(missionImpossible);
		missionImpossiblePhoto5.setPhoto(changeFileToBytes("src/main/resources/templates/images/MissionImpossible Scense5.jpg"));
		missionImpossiblePhotos.add(missionImpossiblePhoto5);
		missionImpossible.setPhotos(missionImpossiblePhotos);
		
		movies.add(missionImpossible); //Movie MissionImpossible
		
		Movie lokiS1 = new Movie(); // Loki Serie
		lokiS1.setTitle("Loki Season1");
		lokiS1.setCountry("UnitedStates");
		lokiS1.setCreatedBy("Michael Waldron");
		lokiS1.setStarring(" Tom Hiddleston,  Sophia Di Martino,  Wunmi Mosaku,  Eugene Cordero,  Ke Huy Quan,  Owen Wilson,");
		lokiS1.setType(Type.SERIES);
		lokiS1.setRating(8.1);
		lokiS1.setRuntime(52);
		lokiS1.setReleaseDate(LocalDate.of(2021, 6, 9));
		lokiS1.setView(390);
		
		lokiS1.getGeneres().add(drama);
		lokiS1.getGeneres().add(scienceFiction);
		lokiS1.getGeneres().add(fantasy);
		drama.getMovies().add(lokiS1);
		scienceFiction.getMovies().add(lokiS1);
		fantasy.getMovies().add(lokiS1);
		
		lokiS1.setReleaseYear(year2021);
		
		Synopsis synopsisForLokiS1 = new Synopsis();
		synopsisForLokiS1.setSynopsisEng("Loki is arrested by the Time Variance Authority (TVA) when he creates a new timeline after escaping from the Battle of New York with the Tesseract in 2012.");
		synopsisForLokiS1.setSynopsisMm("Loki သည် 2012 ခုနှစ်တွင် Tesseract နှင့် New York တိုက်ပွဲမှထွက်ပြေးပြီးနောက် timeline အသစ်ကိုဖန်တီးသောအခါ Time Variance Authority (TVA) မှဖမ်းဆီးခဲ့သည်။");
		
		lokiS1.setSynopsis(synopsisForLokiS1);
		
		lokiS1.setImage(changeFileToBytes("src/main/resources/templates/images/Loki_season_1.jpg"));
		
		List<Episodes> lokiS1Ep = new ArrayList<>();
		Episodes ep1ForLokiS1 = new Episodes();
		ep1ForLokiS1.setEpisode(1);
		ep1ForLokiS1.setLink("Autumn Durald Arkapaw (season 1) Ep 1");
		ep1ForLokiS1.setQuality(Quality._1080P);
		ep1ForLokiS1.setMovie(lokiS1);
		
		Episodes ep2ForLokiS1 = new Episodes();
		ep2ForLokiS1.setEpisode(2);
		ep2ForLokiS1.setLink("Autumn Durald Arkapaw (season 1) Ep 2");
		ep2ForLokiS1.setQuality(Quality._1080P);
		ep2ForLokiS1.setMovie(lokiS1);
		
		Episodes ep3ForLokiS1 = new Episodes();
		ep3ForLokiS1.setEpisode(3);
		ep3ForLokiS1.setLink("Autumn Durald Arkapaw (season 1) Ep 3");
		ep3ForLokiS1.setQuality(Quality._1080P);
		ep3ForLokiS1.setMovie(lokiS1);
		
		Episodes ep4ForLokiS1 = new Episodes();
		ep4ForLokiS1.setEpisode(4);
		ep4ForLokiS1.setLink("Autumn Durald Arkapaw (season 1) Ep 4");
		ep4ForLokiS1.setQuality(Quality._1080P);
		ep4ForLokiS1.setMovie(lokiS1);
		
		Episodes ep5ForLokiS1 = new Episodes();
		ep5ForLokiS1.setEpisode(5);
		ep5ForLokiS1.setLink("Autumn Durald Arkapaw (season 1) Ep 5");
		ep5ForLokiS1.setQuality(Quality._1080P);
		ep5ForLokiS1.setMovie(lokiS1);
		
		Episodes ep6ForLokiS1 = new Episodes();
		ep6ForLokiS1.setEpisode(6);
		ep6ForLokiS1.setLink("Autumn Durald Arkapaw (season 1) Ep 6");
		ep6ForLokiS1.setQuality(Quality._1080P);
		ep6ForLokiS1.setMovie(lokiS1);
		
		lokiS1Ep.add(ep1ForLokiS1);
		lokiS1Ep.add(ep2ForLokiS1);
		lokiS1Ep.add(ep3ForLokiS1);
		lokiS1Ep.add(ep4ForLokiS1);
		lokiS1Ep.add(ep5ForLokiS1);
		lokiS1Ep.add(ep6ForLokiS1);
		
		lokiS1.setEpisodes(lokiS1Ep);
		
		List<Photos> lokiS1Photos = new ArrayList<>();
		Photos lokiS1Photo2 = new Photos();
		lokiS1Photo2.setMovie(lokiS1);
		lokiS1Photo2.setPhoto(changeFileToBytes("src/main/resources/templates/images/LokiS1_2.jpg"));
		lokiS1Photos.add(lokiS1Photo2);
		Photos lokiS1Photo3 = new Photos();
		lokiS1Photo3.setMovie(lokiS1);
		lokiS1Photo3.setPhoto(changeFileToBytes("src/main/resources/templates/images/LokiS1_3.jpg"));
		lokiS1Photos.add(lokiS1Photo3);
		Photos lokiS1Photo6 = new Photos();
		lokiS1Photo6.setMovie(lokiS1);
		lokiS1Photo6.setPhoto(changeFileToBytes("src/main/resources/templates/images/LokiS1_6.jpg"));
		lokiS1Photos.add(lokiS1Photo6);
		lokiS1.setPhotos(lokiS1Photos);
		
		movies.add(lokiS1); //Serie LokiS1

		Movie lokiS2 = new Movie(); // Loki Serie season 2
		lokiS2.setTitle("Loki Season2");
		lokiS2.setCountry("UnitedStates");
		lokiS2.setCreatedBy("Michael Waldron");
		lokiS2.setStarring(" Tom Hiddleston,  Sophia Di Martino,  Wunmi Mosaku,  Eugene Cordero,  Ke Huy Quan,  Owen Wilson,");
		lokiS2.setType(Type.SERIES);
		lokiS2.setRating(8.2);
		lokiS2.setRuntime(52);
		lokiS2.setReleaseDate(LocalDate.of(2023, 10, 5));
		lokiS2.setView(600);
		
		lokiS2.getGeneres().add(drama);
		lokiS2.getGeneres().add(scienceFiction);
		lokiS2.getGeneres().add(fantasy);
		drama.getMovies().add(lokiS2);
		scienceFiction.getMovies().add(lokiS2);
		fantasy.getMovies().add(lokiS2);
		
		lokiS2.setReleaseYear(year2023);
		
		Synopsis synopsisForLokiS2 = new Synopsis();
		synopsisForLokiS2.setSynopsisEng("After stealing the Tesseract, Loki comes into contact with a mysterious organization that gives him an ominous ultimatum, either fix the timeline or cease to exist completely.");
		synopsisForLokiS2.setSynopsisMm("Tesseract ကို ခိုးယူပြီးနောက် Loki သည် အချိန်ဇယားကို ပြင်ဆင်ရန် သို့မဟုတ် လုံးဝတည်ရှိရန် ရပ်တန့်စေမည့် လျှို့ဝှက်ဆန်းကြယ်သော အဖွဲ့အစည်းတစ်ခုနှင့် အဆက်အသွယ်ရလာသည်။");
		
		lokiS2.setSynopsis(synopsisForLokiS2);
		
		lokiS2.setImage(changeFileToBytes("src/main/resources/templates/images/Loki_season_2.jpg"));
		
		List<Episodes> lokiS2Ep = new ArrayList<>();
		Episodes ep1ForLokiS2 = new Episodes();
		ep1ForLokiS2.setEpisode(1);
		ep1ForLokiS2.setLink("Autumn Durald Arkapaw (season 2) Ep 1");
		ep1ForLokiS2.setQuality(Quality._1080P);
		ep1ForLokiS2.setMovie(lokiS2);
		
		Episodes ep2ForLokiS2 = new Episodes();
		ep2ForLokiS2.setEpisode(2);
		ep2ForLokiS2.setLink("Autumn Durald Arkapaw (season 2) Ep 2");
		ep2ForLokiS2.setQuality(Quality._1080P);
		ep2ForLokiS2.setMovie(lokiS2);
		
		Episodes ep3ForLokiS2 = new Episodes();
		ep3ForLokiS2.setEpisode(3);
		ep3ForLokiS2.setLink("Autumn Durald Arkapaw (season 2) Ep 3");
		ep3ForLokiS2.setQuality(Quality._1080P);
		ep3ForLokiS2.setMovie(lokiS2);
		
		Episodes ep4ForLokiS2 = new Episodes();
		ep4ForLokiS2.setEpisode(4);
		ep4ForLokiS2.setLink("Autumn Durald Arkapaw (season 2) Ep 4");
		ep4ForLokiS2.setQuality(Quality._1080P);
		ep4ForLokiS2.setMovie(lokiS2);
		
		Episodes ep5ForLokiS2 = new Episodes();
		ep5ForLokiS2.setEpisode(5);
		ep5ForLokiS2.setLink("Autumn Durald Arkapaw (season 2) Ep 5");
		ep5ForLokiS2.setQuality(Quality._1080P);
		ep5ForLokiS2.setMovie(lokiS2);
		
		Episodes ep6ForLokiS2 = new Episodes();
		ep6ForLokiS2.setEpisode(6);
		ep6ForLokiS2.setLink("Autumn Durald Arkapaw (season 2) Ep 6");
		ep6ForLokiS2.setQuality(Quality._1080P);
		ep6ForLokiS2.setMovie(lokiS2);
		
		lokiS2Ep.add(ep1ForLokiS2);
		lokiS2Ep.add(ep2ForLokiS2);
		lokiS2Ep.add(ep3ForLokiS2);
		lokiS2Ep.add(ep4ForLokiS2);
		lokiS2Ep.add(ep5ForLokiS2);
		lokiS2Ep.add(ep6ForLokiS2);
		
		lokiS2.setEpisodes(lokiS2Ep);

		List<Photos> lokiS2Photos = new ArrayList<>();
		Photos lokiS2Photo2 = new Photos();
		lokiS2Photo2.setMovie(lokiS2);
		lokiS2Photo2.setPhoto(changeFileToBytes("src/main/resources/templates/images/LokiS1_2.jpg"));
		lokiS2Photos.add(lokiS2Photo2);
		Photos lokiS2Photo3 = new Photos();
		lokiS2Photo3.setMovie(lokiS2);
		lokiS2Photo3.setPhoto(changeFileToBytes("src/main/resources/templates/images/LokiS1_3.jpg"));
		lokiS2Photos.add(lokiS2Photo3);
		Photos lokiS2Photo6 = new Photos();
		lokiS2Photo6.setMovie(lokiS2);
		lokiS2Photo6.setPhoto(changeFileToBytes("src/main/resources/templates/images/LokiS1_6.jpg"));
		lokiS2Photos.add(lokiS2Photo6);
		lokiS2.setPhotos(lokiS2Photos);
		
		movies.add(lokiS2); //Serie LokiS2
		
		Movie freedy = new Movie();// Movie Five Nights at Freddy's
		freedy.setTitle("Five Nights at Freddy's");
		freedy.setCountry("UnitedStates");
		freedy.setCreatedBy("Emma Tammi");
		freedy.setStarring("Josh Hutcherson,  Piper Rubio,  Elizabeth Lail,  Matthew Lillard,  Mary Stuart Masterson,  Kat Conner Sterling,  David Lind,  Christian Stokes,  Joseph Poliquin,  Grant Feely,  Asher Colton Spence,  David Huston Doty,  Liam Hendrix,  Jophielle Love,  Tadasay Young,  Michael P. Sullivan,  Wyatt Parker,  Lucas Grant,  Jessica Blackmore,  Garrett Hines,  Ryan Reinike,");
		freedy.setType(Type.MOVIE);
		freedy.setRating(5.5);
		freedy.setRuntime(110);
		freedy.setReleaseDate(LocalDate.of(2023, 10, 27));
		freedy.setView(856);
		freedy.getGeneres().add(horror);
		freedy.getGeneres().add(mystery);
		
		horror.getMovies().add(freedy);
		mystery.getMovies().add(freedy);
		
		freedy.setReleaseYear(year2023);
		
		freedy.setImage(changeFileToBytes("src/main/resources/templates/images/Five Nights.jpg"));
		
		Synopsis synopsisForFreedy = new Synopsis();
		synopsisForFreedy.setSynopsisEng("Recently fired and desperate for work, a troubled young man named Mike agrees to take a position as a night security guard at an abandoned theme restaurant: Freddy Fazbear's Pizzeria. But he soon discovers that nothing at Freddy's is what it seems.");
		synopsisForFreedy.setSynopsisMm("မကြာသေးမီက အလုပ်ထုတ်ခံရပြီး စိတ်အားထက်သန်သူ Mike ဟုခေါ်သော စိတ်ပူပန်သောကရောက်နေသည့် လူငယ်တစ်ဦးသည် စွန့်ပစ်ထားသော စားသောက်ဆိုင်တစ်ခုဖြစ်သည့် Freddy Fazbear's Pizzeria တွင် ညလုံခြုံရေးအစောင့်အဖြစ် တာဝန်ထမ်းဆောင်ရန် သဘောတူခဲ့သည်။ သို့သော် Freddy's တွင် မည်သည့်အရာကမျှ ဖြစ်ပုံမပေါ်ကြောင်း မကြာမီ သူတွေ့ရှိခဲ့သည်။");
		
		freedy.setSynopsis(synopsisForFreedy);
		
		List<Episodes> freedyEp = new ArrayList<>();
		Episodes ep1ForFreedy = new Episodes();
		ep1ForFreedy.setEpisode(1);
		ep1ForFreedy.setLink("Five Nights at Freddy's link");
		ep1ForFreedy.setQuality(Quality._1080P);
		ep1ForFreedy.setMovie(freedy);
		freedyEp.add(ep1ForFreedy);
		
		freedy.setEpisodes(freedyEp);
		
		List<Photos> freedyPhotos = new ArrayList<>();
		Photos freedyPhoto1 = new Photos();
		freedyPhoto1.setMovie(freedy);
		freedyPhoto1.setPhoto(changeFileToBytes("src/main/resources/templates/images/Five Nights1.jpg"));
		freedyPhotos.add(freedyPhoto1);
		Photos freedyPhoto2 = new Photos();
		freedyPhoto2.setMovie(freedy);
		freedyPhoto2.setPhoto(changeFileToBytes("src/main/resources/templates/images/Five Nights2.jpg"));
		freedyPhotos.add(freedyPhoto2);
		Photos freedyPhoto3 = new Photos();
		freedyPhoto3.setMovie(freedy);
		freedyPhoto3.setPhoto(changeFileToBytes("src/main/resources/templates/images/Five Nights3.jpg"));
		freedyPhotos.add(freedyPhoto3);
		freedy.setPhotos(freedyPhotos);
		
		movies.add(freedy); //Movie Five Nights
		
		Movie oppenheimer = new Movie();
		oppenheimer.setTitle("Oppenheimer");
		oppenheimer.setCountry("UnitedStates");
		oppenheimer.setCreatedBy("Christopher Nolan");
		oppenheimer.setStarring("Cillian Murphy,  Emily Blunt,  Matt Damon,  Robert Downey Jr.,  Florence Pugh,  Josh Hartnett,  Casey Affleck,  Rami Malek,  Kenneth Branagh,  Benny Safdie,  Jason Clarke,  Dylan Arnold,  Tom Conti,  James D'Arcy");
		oppenheimer.setType(Type.MOVIE);
		oppenheimer.setRating(8.2);
		oppenheimer.setRuntime(185);
		oppenheimer.setReleaseDate(LocalDate.of(2023, 7, 21));
		oppenheimer.setView(920);
		oppenheimer.getGeneres().add(drama);
		oppenheimer.getGeneres().add(history);
		
		drama.getMovies().add(oppenheimer);
		history.getMovies().add(oppenheimer);
		
		oppenheimer.setReleaseYear(year2023);
		
		Synopsis synopsisForoppenheimer = new Synopsis();
		synopsisForoppenheimer.setSynopsisEng("During World War II, Lt. Gen. Leslie Groves Jr. appoints physicist J. Robert Oppenheimer to work on the top-secret Manhattan Project. Oppenheimer and a team of scientists spend years developing and designing the atomic bomb. Their work comes to fruition on July 16, 1945, as they witness the world's first nuclear explosion, forever changing the course of history.");
		synopsisForoppenheimer.setSynopsisMm("ဒုတိယကမ္ဘာစစ်အတွင်း၊ ဒုတိယဗိုလ်ချုပ်ကြီး Leslie Groves Jr. သည် ထိပ်တန်းလျှို့ဝှက် Manhattan Project တွင် ရူပဗေဒပညာရှင် J. Robert Oppenheimer ကို ခန့်အပ်ခဲ့သည်။ Oppenheimer နှင့် သိပ္ပံပညာရှင်အဖွဲ့တစ်ဖွဲ့သည် အဏုမြူဗုံးကို တီထွင်ဖန်တီးရန် နှစ်ပေါင်းများစွာ အချိန်ယူခဲ့ကြသည်။ ကမ္ဘာ့ပထမဆုံးနျူကလီးယားပေါက်ကွဲမှုကိုတွေ့မြင်ရပြီး သမိုင်းလမ်းကြောင်းကို ထာဝရပြောင်းလဲစေသည့် ၎င်းတို့၏လုပ်ငန်းသည် ဇူလိုင် ၁၆ ရက်၊");
		
		oppenheimer.setSynopsis(synopsisForoppenheimer);
		
		oppenheimer.setImage(changeFileToBytes("src/main/resources/templates/images/oppenhiemer.jpg"));
		
		List<Episodes> oppenheimerEp = new ArrayList<>();
		Episodes ep1oppenheimer = new Episodes();
		ep1oppenheimer.setEpisode(1);
		ep1oppenheimer.setLink("Oppenhiemer link");
		ep1oppenheimer.setQuality(Quality._4K);
		ep1oppenheimer.setMovie(oppenheimer);
		oppenheimerEp.add(ep1oppenheimer);
		
		oppenheimer.setEpisodes(oppenheimerEp);
		
		List<Photos> oppenheimerPhotos = new ArrayList<>();
		Photos oppenheimerPhoto1 = new Photos();
		oppenheimerPhoto1.setMovie(oppenheimer);
		oppenheimerPhoto1.setPhoto(changeFileToBytes("src/main/resources/templates/images/oppenhiemer-bg.jpg"));
		oppenheimerPhotos.add(oppenheimerPhoto1);
		oppenheimer.setPhotos(oppenheimerPhotos);
		
		movies.add(oppenheimer); //Movie Oppenhiemer
		
		
		Movie annabelle = new Movie();
		annabelle.setTitle("Annabelle Comes Home (2019)");
		annabelle.setCountry("UnitedStates");
		annabelle.setCreatedBy("Gary Dauberman");
		annabelle.setStarring("Vera Farmiga,  Patrick Wilson,  Mckenna Grace,  Madison Iseman,  Katie Sarife,  Michael Cimino,  Samara Lee,  Paul Dean,  Luca Luhan,  Alison White,  Stephen Blackehart,  Sade Katarina,  Kenzie Caplan,  Gary-7");
		annabelle.setType(Type.MOVIE);
		annabelle.setRating(6.7);
		annabelle.setRuntime(606);
		annabelle.setReleaseDate(LocalDate.of(2019, 6, 26));
		annabelle.setView(220);
		annabelle.getGeneres().add(horror);
		annabelle.getGeneres().add(mystery);
		annabelle.getGeneres().add(thriller);
		
		horror.getMovies().add(annabelle);
		mystery.getMovies().add(annabelle);
		thriller.getMovies().add(annabelle);
		
		annabelle.setReleaseYear(year2019);
		
		Synopsis synopsisForannabelle = new Synopsis();
		synopsisForannabelle.setSynopsisEng("Judy and her babysitter are left alone in her house after her parents leave to investigate a case. However, an unexpected guest sets Annabelle free, unleashing demonic activity in the house.");
		synopsisForannabelle.setSynopsisMm("Judy နှင့် သူမ၏ ကလေးထိန်းသည် သူမ၏ မိဘများ ထွက်ခွာသွားပြီးနောက် သူမ၏ အိမ်တွင် တစ်ယောက်တည်း ကျန်ရစ်ခဲ့သည်။ သို့သော်၊ မျှော်လင့်မထားသောဧည့်သည်တစ်ဦးသည် Annabelle ကိုလွတ်မြောက်စေပြီး အိမ်ထဲတွင် နတ်ဆိုးလှုပ်ရှားမှုကို လွှတ်မြောက်စေသည်။");
		
		annabelle.setSynopsis(synopsisForannabelle);
		
		annabelle.setImage(changeFileToBytes("src/main/resources/templates/images/Annabelle.jpg"));
		
		List<Episodes> annabelleEp = new ArrayList<>();
		Episodes ep1annabelle = new Episodes();
		ep1annabelle.setEpisode(1);
		ep1annabelle.setLink("Annabelle link");
		ep1annabelle.setQuality(Quality._4K);
		ep1annabelle.setMovie(annabelle);
		annabelleEp.add(ep1annabelle);
		
		annabelle.setEpisodes(annabelleEp);
		
		List<Photos> annabellePhotos = new ArrayList<>();
		Photos annabellePhoto1 = new Photos();
		annabellePhoto1.setMovie(annabelle);
		annabellePhoto1.setPhoto(changeFileToBytes("src/main/resources/templates/images/Annabelle-bg.jpg"));
		annabellePhotos.add(annabellePhoto1);
		annabelle.setPhotos(annabellePhotos);
		
		movies.add(annabelle); //Movie Annabelle
		
		
		Movie asih = new Movie();
		asih.setTitle("Asih");
		asih.setCountry("Indonesia");
		asih.setCreatedBy("Awi Suryadi");
		asih.setStarring(" Shareefa Daanish,  Citra Kirana,  Darius Sinathrya,  Alex Abbad,  Djenar Maesa Ayu,  Egy Fedly,  Marini Soerjosoemarno,  Siti Masitoh,  Tomy Babap,  Iang Darmawan");
		asih.setType(Type.MOVIE);
		asih.setRating(7.0);
		asih.setRuntime(76);
		asih.setReleaseDate(LocalDate.of(2018, 10, 3));
		asih.setView(456);
		asih.getGeneres().add(horror);
		asih.getGeneres().add(drama);
		
		horror.getMovies().add(asih);
		drama.getMovies().add(asih);
		
		asih.setReleaseYear(year2018);
		
		Synopsis synopsisForAsih = new Synopsis();
		synopsisForAsih.setSynopsisEng("Puspita and Andi are very happy to have a baby. The happiness turns into a threat with the presence of Asih, who committed suicide.");
		synopsisForAsih.setSynopsisMm("Puspita နဲ့ Andi က ကလေးမွေးရတာ အရမ်းပျော်ပါတယ်။ ပျော်ရွှင်မှုသည် မိမိကိုယ်ကို သတ်သေခဲ့သော Asih ထံတွင် ခြိမ်းခြောက်မှုအဖြစ် ပြောင်းလဲသွားသည်။");
		
		asih.setSynopsis(synopsisForAsih);
		
		asih.setImage(changeFileToBytes("src/main/resources/templates/images/Asih.jpg"));
		
		List<Episodes> asihEp = new ArrayList<>();
		Episodes ep1Asih = new Episodes();
		ep1Asih.setEpisode(1);
		ep1Asih.setLink("Asih link");
		ep1Asih.setQuality(Quality._1080P);
		ep1Asih.setMovie(asih);
		asihEp.add(ep1Asih);
		
		asih.setEpisodes(asihEp);
		
		List<Photos> asihPhotos = new ArrayList<>();
		Photos asihPhoto1 = new Photos();
		asihPhoto1.setMovie(asih);
		asihPhoto1.setPhoto(changeFileToBytes("src/main/resources/templates/images/Asih-bg.jpg"));
		asihPhotos.add(asihPhoto1);
		asih.setPhotos(asihPhotos);
		
		movies.add(asih); //Movie Asih1
		
		
		Movie asih2 = new Movie();
		asih2.setTitle("Asih 2");
		asih2.setCountry("Indonesia");
		asih2.setCreatedBy("Awi Suryadi");
		asih2.setStarring("Shareefa Daanish,  Marsha Timothy,  Ario Bayu,  Anantya Rezky Kirana,  Ruth Marini,  Graciella Abigail,  Ully Triani,  Darius Sinathrya,  Marini Soerjosoemarno,  Ingrid Widjanarko,  Kenya Nindia,  Sarah Presli,  Citra Kirana");
		asih2.setType(Type.MOVIE);
		asih2.setRating(7.3);
		asih2.setRuntime(105);
		asih2.setReleaseDate(LocalDate.of(2020, 12, 24));
		asih2.setView(79);
		asih2.getGeneres().add(horror);
		asih2.getGeneres().add(drama);
		
		horror.getMovies().add(asih2);
		drama.getMovies().add(asih2);
		
		asih2.setReleaseYear(year2020);
		
		Synopsis synopsisForAsih2 = new Synopsis();
		synopsisForAsih2.setSynopsisEng("After adopting Ana, Sylvia realizes that she did not only bring Ana home, but also Asih, the ghost who has been Ana's foster mother.");
		synopsisForAsih2.setSynopsisMm("Ana ကိုမွေးစားပြီးနောက်၊ Sylvia သည် Ana ၏မွေးစားမိခင်ဖြစ်ခဲ့သည့် Asih ကို Ana အိမ်ကိုခေါ်ဆောင်လာသည်ကို သိရှိလာသည်။");
		
		asih2.setSynopsis(synopsisForAsih2);
		
		asih2.setImage(changeFileToBytes("src/main/resources/templates/images/Asih2.jpg"));
		
		List<Episodes> asih2Ep = new ArrayList<>();
		Episodes ep1Asih2 = new Episodes();
		ep1Asih2.setEpisode(1);
		ep1Asih2.setLink("Asih2 link");
		ep1Asih2.setQuality(Quality._1080P);
		ep1Asih2.setMovie(asih2);
		asih2Ep.add(ep1Asih2);
		
		asih2.setEpisodes(asih2Ep);
		
		List<Photos> asih2Photos = new ArrayList<>();
		Photos asih2Photo1 = new Photos();
		asih2Photo1.setMovie(asih2);
		asih2Photo1.setPhoto(changeFileToBytes("src/main/resources/templates/images/Asih2-bg.jpg"));
		asih2Photos.add(asihPhoto1);
		asih2.setPhotos(asih2Photos);
		
		movies.add(asih2); //Movie Asih1
		
		return movies;
	}

}
