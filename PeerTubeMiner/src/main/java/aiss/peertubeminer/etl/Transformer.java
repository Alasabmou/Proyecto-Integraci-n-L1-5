package aiss.peertubeminer.etl;

import java.util.List;

import org.springframework.stereotype.Service;

import aiss.peertubeminer.model.peertube.Caption;
import aiss.peertubeminer.model.peertube.CaptionSearch;
import aiss.peertubeminer.model.peertube.Channel;
import aiss.peertubeminer.model.peertube.Comment;
import aiss.peertubeminer.model.peertube.CommentSearch;
import aiss.peertubeminer.model.peertube.User;
import aiss.peertubeminer.model.peertube.Video;
import aiss.peertubeminer.model.videominer.VMCaption;
import aiss.peertubeminer.model.videominer.VMChannel;
import aiss.peertubeminer.model.videominer.VMComment;
import aiss.peertubeminer.model.videominer.VMUser;
import aiss.peertubeminer.model.videominer.VMVideo;
import aiss.peertubeminer.service.CaptionService;
import aiss.peertubeminer.service.CommentService;

@Service
public class Transformer {

    private final CommentService commentService;
    private final CaptionService captionService;

    public Transformer(CommentService commentService, CaptionService captionService) {
        this.commentService = commentService;
        this.captionService = captionService;
    }

    public VMChannel transform(Channel ptChannel, List<Video> ptVideos, Integer maxComments) {
        VMChannel vmChannel = transformChannel(ptChannel);
        
        transformVideos(ptVideos, vmChannel, maxComments);
        
        return vmChannel;
    }

    public VMChannel transformChannel(Channel ptChannel) {
        VMChannel vmChannel = new VMChannel();
        vmChannel.setId(ptChannel.getId().toString());
        vmChannel.setName(ptChannel.getName());
        vmChannel.setDescription(ptChannel.getDescription());
        vmChannel.setCreatedTime(ptChannel.getCreatedAt());
        return vmChannel;
    }

    public void transformVideos(List<Video> ptVideos, VMChannel vmChannel, Integer maxComments) {
        for (Video ptVideo : ptVideos) {
            VMUser author = transformUser(ptVideo.getAccount());
            
            VMVideo vmVideo = new VMVideo();
            vmVideo.setId(ptVideo.getId().toString());
            vmVideo.setName(ptVideo.getName());
            vmVideo.setDescription(ptVideo.getDescription());
            vmVideo.setReleaseTime(ptVideo.getPublishedAt());
            vmVideo.setAuthor(author);

            CommentSearch commentSearch = commentService.getComments(ptVideo.getId().toString(), maxComments);
            if (commentSearch != null && commentSearch.getData() != null) {
                transformComments(commentSearch.getData(), vmVideo);
            }

            CaptionSearch captionSearch = captionService.getCaptions(ptVideo.getId().toString());
            if (captionSearch != null && captionSearch.getData() != null) {
                transformCaptions(captionSearch.getData(), vmVideo);
            }

            vmChannel.getVideos().add(vmVideo);
        }
    }

    public void transformComments(List<Comment> ptComments, VMVideo vmVideo) {
        for (Comment ptComment : ptComments) {
            VMComment vmComment = new VMComment();
            vmComment.setId(ptComment.getId().toString());
            vmComment.setText(ptComment.getText());
            vmComment.setCreatedOn(ptComment.getCreatedAt());
            
            vmVideo.getComments().add(vmComment);
        }
    }

    public void transformCaptions(List<Caption> ptCaptions, VMVideo vmVideo) {
        for (Caption ptCaption : ptCaptions) {
            VMCaption vmCaption = new VMCaption();
            vmCaption.setId(java.util.UUID.randomUUID().toString());
            vmCaption.setName(ptCaption.getCaptionPath());
            vmCaption.setLanguage(ptCaption.getLanguage().getLabel());
            
            vmVideo.getCaptions().add(vmCaption);
        }
    }

    public VMUser transformUser(User ptUser) {
        VMUser vmUser = new VMUser();
        vmUser.setId(null);
        vmUser.setName(ptUser.getName());
        vmUser.setUser_link(ptUser.getUrl());
        
        String pictureLink = null;
        if (ptUser.getAvatars() != null && !ptUser.getAvatars().isEmpty()) {
            pictureLink = ptUser.getAvatars().get(0).getFileUrl();
        }
        vmUser.setPicture_link(pictureLink);

        return vmUser;
    }
}