package aiss.dailymotionminer.etl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import aiss.dailymotionminer.model.dailymotion.DmChannel;
import aiss.dailymotionminer.model.dailymotion.DmSubtitles;
import aiss.dailymotionminer.model.dailymotion.DmUser;
import aiss.dailymotionminer.model.dailymotion.DmVideos;
import aiss.dailymotionminer.model.dailymotion.Subtitle;
import aiss.dailymotionminer.model.dailymotion.Video;
import aiss.dailymotionminer.model.videominer.VMCaption;
import aiss.dailymotionminer.model.videominer.VMChannel;
import aiss.dailymotionminer.model.videominer.VMComment;
import aiss.dailymotionminer.model.videominer.VMUser;
import aiss.dailymotionminer.model.videominer.VMVideo;
import aiss.dailymotionminer.service.SubtitleService;

@Component
public class Transformer {
	
	@Autowired
	private SubtitleService subtitleService;

	public VMChannel transformChannel(DmChannel channel, DmVideos videos, DmUser user) {
		VMChannel res = new VMChannel(channel.getId(), channel.getUsername(), channel.getDescription(), channel.getCreatedTime().toString());
		
		List<VMVideo> videoList = transformVideo(videos, user);
		res.setVideos(videoList);

		return res;
	}

	private List<VMVideo> transformVideo(DmVideos videos, DmUser user) {
		List<VMVideo> res = new ArrayList<>();

		VMUser author = transformUser(user);

		List<Video> listVideos = videos.getList();
		for(Video v: listVideos) {
			VMVideo newVideo = new VMVideo(v.getId(), v.getTitle(), v.getDescription(), v.getCreatedTime().toString(), author);
			
			List<VMComment> comments = transformComments(v);
			newVideo.setComments(comments);
			
			List<VMCaption> captions = transformCaption(v.getId());
			newVideo.setCaptions(captions);

			res.add(newVideo);
		}

		return res;
	}


	private static VMUser transformUser(DmUser user) {
		VMUser author = new VMUser(user.getScreenname(), user.getUrl(), user.getAvatar720Url());
		return author;
	}

	private static List<VMComment> transformComments(Video v) {
		List<String> tags = v.getTags();
		List<VMComment> comments = new ArrayList<>();
		for (String s: tags) {
			VMComment comment = new VMComment(s);
			comments.add(comment);
		}
		return comments;
	}
	
	private List<VMCaption> transformCaption(String videoId) {
		DmSubtitles subtitles = subtitleService.getSubtitles(videoId);
		List<Subtitle> subtitleList = subtitles.getList();
		List<VMCaption> captions = new ArrayList<>();

		if (!subtitleList.isEmpty()) {
			for(Subtitle s: subtitleList) {
			VMCaption caption = new VMCaption(s.getId(), s.getUrl(), s.getLanguage());
			captions.add(caption);
		}
		}
		return captions;
	}
}
