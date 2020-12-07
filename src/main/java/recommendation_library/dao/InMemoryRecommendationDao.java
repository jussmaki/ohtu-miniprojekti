/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommendation_library.dao;

import recommendation_library.domain.BookRecommendation;

import java.util.*;
import java.util.function.Function;
import recommendation_library.domain.BlogRecommendation;
import recommendation_library.domain.PodcastRecommendation;
import recommendation_library.domain.Tag;

import recommendation_library.domain.TimeMemory;
import recommendation_library.domain.VideoRecommendation;

/**
 * @author jenni.makinen
 */
public class InMemoryRecommendationDao implements RecommendationDao {

    private List<BookRecommendation> bookRecommendations;
    private List<VideoRecommendation> videoRecommendations;
    private List<TimeMemory> timeStamps;
    private List<BlogRecommendation> blogRecommendations;
    private List<PodcastRecommendation> podcastRecommendations;
    private List<Tag> tags;

    public InMemoryRecommendationDao() {
        this.bookRecommendations = new ArrayList<>();
        this.videoRecommendations = new ArrayList<>();
        this.timeStamps = new ArrayList<>();
        this.blogRecommendations = new ArrayList<>();
        this.podcastRecommendations = new ArrayList<>();
        this.tags = new ArrayList<>();
    }

    @Override
    public void createBookRecommendation(String author, String title, String description, String isbn, int pageCount) {
        String addDate = java.time.LocalDate.now().toString();
        this.bookRecommendations.add(new BookRecommendation(this.bookRecommendations.size() + 1, author, title, description, isbn, pageCount, addDate));
    }

    @Override
    public List<BookRecommendation> getAllBookRecommendations() {
        return this.bookRecommendations;
    }

    @Override
    public void editBookRecommendation(String title, String fieldToBeEdited, String newValue) {
        for (BookRecommendation b : bookRecommendations) {
            if (b.getTitle().equals(title)) {
                editBookMatchingField(b, fieldToBeEdited, newValue);
            }
        }
    }

    private void editBookMatchingField(BookRecommendation b, String fieldToBeEdited, String newValue) {
        fieldToBeEdited = fieldToBeEdited.toLowerCase();

        Map<String, Function<String, Boolean>> map = new HashMap<>();

        map.put("author", b::setAuthor);
        map.put("title", b::setTitle);
        map.put("description", b::setDescription);
        map.put("isbn", b::setIsbn);
        map.put("pagecount", s -> b.setPageCount(Integer.parseInt(s)));

        map.get(fieldToBeEdited).apply(newValue);
    }

    @Override
    public void deleteBookByTitle(String title) {
        BookRecommendation toBeRemoved = null;
        for (BookRecommendation b : bookRecommendations) {
            if (b.getTitle().equals(title)) {
                toBeRemoved = b;
                break;
            }
        }
        if (toBeRemoved != null) {
            bookRecommendations.remove(toBeRemoved);
        }
    }

    @Override
    public void createVideoRecommendation(String url, String title, String description) {
        String addDate = java.time.LocalDate.now().toString();
        videoRecommendations.add(new VideoRecommendation(this.videoRecommendations.size() + 1, url, title, description, addDate));
    }

    @Override
    public List<VideoRecommendation> getAllVideoRecommendations() {
        return this.videoRecommendations;
    }

    @Override
    public void editVideoRecommendation(String title, String fieldToBeEdited, String newValue) {
        for (VideoRecommendation v : videoRecommendations) {
            if (v.getTitle().equals(title)) {
                editVideoMatchingField(v, fieldToBeEdited, newValue);
            }
        }
    }

    private void editVideoMatchingField(VideoRecommendation v, String fieldToBeEdited, String newValue) {
        fieldToBeEdited = fieldToBeEdited.toLowerCase();
        Map<String, Function<String, Boolean>> map = new HashMap<>();
        map.put("title", v::setTitle);
        map.put("url", v::setUrl);
        map.put("description", v::setDescription);
        System.err.println(fieldToBeEdited);
        map.get(fieldToBeEdited).apply(newValue);
    }

    @Override
    public int getVideoIdByTitle(String title) {
        for (VideoRecommendation v : videoRecommendations) {
            if (v.getTitle().equals(title)) {
                return v.getId();
            }
        }
        return 0;
    }
    
    @Override
    public int getBookIdByTitle(String title) {
        for (BookRecommendation b : bookRecommendations) {
            if (b.getTitle().equals(title)) {
                return b.getId();
            }
        }
        return 0;
    }
    
    @Override
    public int getBlogIdByTitle(String title) {
        for (BlogRecommendation b : blogRecommendations) {
            if (b.getTitle().equals(title)) {
                return b.getId();
            }
        }
        return 0;
    }
    
    @Override
    public int getPodcastIdByTitle(String title) {
        for (PodcastRecommendation p : podcastRecommendations) {
            if (p.getTitle().equals(title)) {
                return p.getId();
            }
        }
        return 0;
    }

    @Override
    public void addTimeStampToVideo(int videoId, String timestamp, String comment) {
        this.timeStamps.add(new TimeMemory(this.timeStamps.size() + 1, timestamp, comment, videoId));
    }

    @Override
    public List<TimeMemory> getAllTimestampsForVideo(int videoId) {
        List<TimeMemory> stamps = new ArrayList<>();
        for (TimeMemory t : this.timeStamps) {
            if (t.getVideoId() == videoId) {
                stamps.add(t);
            }
        }
        return stamps;
    }

    @Override
    public void deleteVideoByTitle(String title) {
        VideoRecommendation toBeRemoved = null;
        for (VideoRecommendation v : videoRecommendations) {
            if (v.getTitle().equals(title)) {
                toBeRemoved = v;
                break;
            }
        }
        if (toBeRemoved != null) {
            videoRecommendations.remove(toBeRemoved);
        }
    }

    @Override
    public void editTimestampForVideo(int videoId, int timeStampId, String fieldToBeEdited, String newValue) {
        for (TimeMemory t : this.timeStamps) {
            if (t.getVideoId() == videoId && t.getId() == timeStampId) {
                editTimeStampField(t, fieldToBeEdited, newValue);
            }
        }
    }

    private void editTimeStampField(TimeMemory t, String fieldToBeEdited, String newValue) {
        if (fieldToBeEdited.toLowerCase().equals("comment")) {
            t.setComment(newValue);
        } else if (fieldToBeEdited.toLowerCase().equals("timestamp")) {
            t.setTimeStamp(newValue);
        } else {
            System.err.println(fieldToBeEdited);
        }
    }

    @Override
    public void deleteTimestamp(int videoId, int timeStampId) {
        TimeMemory toBeRemoved = null;
        for (TimeMemory t : this.timeStamps) {
            if (t.getVideoId() == videoId && t.getId() == timeStampId) {
                toBeRemoved = t;
            }
        }

        if (toBeRemoved != null) {
            timeStamps.remove(toBeRemoved);
        }
    }

    @Override
    public void createBlogRecommendation(String url, String title, String author, String description) {
        String addDate = java.time.LocalDate.now().toString();
        this.blogRecommendations.add(new BlogRecommendation(this.blogRecommendations.size() + 1, author, url, title, description, addDate));
    }

    @Override
    public void createPodcastRecommendation(String author, String title, String description, String podcastName) {
        String addDate = java.time.LocalDate.now().toString();
        this.podcastRecommendations.add(new PodcastRecommendation(this.podcastRecommendations.size() + 1, author, title, description, podcastName, addDate));
    }

    @Override
    public List<BlogRecommendation> getAllBlogRecommendations() {
        return this.blogRecommendations;
    }

    @Override
    public List<PodcastRecommendation> getAllPodcastRecommendations() {
        return this.podcastRecommendations;
    }

    @Override
    public int getTimestampIdByTitle(int videoId, String timestamp) {
        for (TimeMemory t : this.timeStamps) {
            if (t.getVideoId() == videoId && t.getTimeStamp() == timestamp) {
                return t.getId();
            }
        }
        return 0;
    }

    @Override
    public void editBlogRecommendation(String title, String fieldToBeEdited, String newValue) {
        for (BlogRecommendation b : blogRecommendations) {
            if (b.getTitle().equals(title)) {
                editBlogMatchingField(b, fieldToBeEdited, newValue);
            }
        }
    }

    private void editBlogMatchingField(BlogRecommendation b, String fieldToBeEdited, String newValue) {
        fieldToBeEdited = fieldToBeEdited.toLowerCase();
        Map<String, Function<String, Boolean>> map = new HashMap<>();
        map.put("title", b::setTitle);
        map.put("author", b::setAuthor);
        map.put("url", b::setUrl);
        map.put("description", b::setDescription);
        System.err.println(fieldToBeEdited);
        map.get(fieldToBeEdited).apply(newValue);
    }

    @Override
    public void editPodcastRecommendation(String title, String fieldToBeEdited, String newValue) {
        for (PodcastRecommendation p : podcastRecommendations) {
            if (p.getTitle().equals(title)) {
                editPodcastMatchingField(p, fieldToBeEdited, newValue);
            }
        }
    }

    private void editPodcastMatchingField(PodcastRecommendation p, String fieldToBeEdited, String newValue) {
        fieldToBeEdited = fieldToBeEdited.toLowerCase();
        Map<String, Function<String, Boolean>> map = new HashMap<>();
        map.put("title", p::setTitle);
        map.put("author", p::setAuthor);
        map.put("podcastname", p::setPodcastName);
        map.put("description", p::setDescription);
        System.err.println(fieldToBeEdited);
        map.get(fieldToBeEdited).apply(newValue);
    }

    @Override
    public void deleteBlogByTitle(String title) {
        BlogRecommendation toBeRemoved = null;
        for (BlogRecommendation b : blogRecommendations) {
            if (b.getTitle().equals(title)) {
                toBeRemoved = b;
                break;
            }
        }
        if (toBeRemoved != null) {
            blogRecommendations.remove(toBeRemoved);
        }
    }

    @Override
    public void deletePodcastByTitle(String title) {
        PodcastRecommendation toBeRemoved = null;
        for (PodcastRecommendation p : podcastRecommendations) {
            if (p.getTitle().equals(title)) {
                toBeRemoved = p;
                break;
            }
        }
        if (toBeRemoved != null) {
            podcastRecommendations.remove(toBeRemoved);
        }
    }

    @Override
    public List<Tag> getAllTagsForBook(int bookId) {
        List<Tag> tags = new ArrayList<>();
        for (BookRecommendation b : bookRecommendations) {
            if (b.getId() == bookId) {
                tags = b.getTags();
            }
        }
        return tags;
    }

    @Override
    public List<Tag> getAllTagsForVideo(int videoId) {
        List<Tag> tags = new ArrayList<>();
        for (VideoRecommendation v : videoRecommendations) {
            if (v.getId() == videoId) {
                tags = v.getTags();
            }
        }
        return tags;
    }

    @Override
    public List<Tag> getAllTagsForBlog(int blogId) {
        List<Tag> tags = new ArrayList<>();
        for (BlogRecommendation b : blogRecommendations) {
            if (b.getId() == blogId) {
                tags = b.getTags();
            }
        }
        return tags;
    }

    @Override
    public List<Tag> getAllTagsForPodcast(int podcastId) {
        List<Tag> tags = new ArrayList<>();
        for (PodcastRecommendation p : podcastRecommendations) {
            if (p.getId() == podcastId) {
                tags = p.getTags();
            }
        }
        return tags;
    }

    @Override
    public void addTagToBook(int bookId, String tagText) {
        Tag newTag = addTag(tagText);
        loop:
        for (BookRecommendation b : bookRecommendations) {
            if (b.getId() == bookId) {
                b.addTag(newTag);
                break loop;
            }
        }
    }

    @Override
    public void addTagToVideo(int videoId, String tagText) {
        Tag newTag = addTag(tagText);
        loop:
        for (VideoRecommendation v : videoRecommendations) {
            if (v.getId() == videoId) {
                v.addTag(newTag);
                break loop;
            }
        }        
    }

    @Override
    public void addTagToBlog(int blogId, String tagText) {
        Tag newTag = addTag(tagText);
        loop:
        for (BlogRecommendation b : blogRecommendations) {
            if (b.getId() == blogId) {
                b.addTag(newTag);
                break loop;
            }
        }
    }

    @Override
    public void addTagToPodcast(int podcastId, String tagText) {
        Tag newTag = addTag(tagText);
        loop:
        for (PodcastRecommendation p : podcastRecommendations) {
            if (p.getId() == podcastId) {
                p.addTag(newTag);
                break loop;
            }
        }
    }

    @Override
    public int getTagId(String tagText) {
        for (Tag t : tags) {
            if (t.getTagText().equals(tagText)) {
                return t.getId();
            }
        }
        return 0;
    }
    
    private Tag addTag(String tagText) {
        Tag newTag = new Tag(this.tags.size() + 1, tagText);
        this.tags.add(newTag);
        return newTag;
    }

    @Override
    public List<Tag> getAllTags() {
        return this.tags;
    }

}
