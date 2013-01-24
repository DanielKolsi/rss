package com.exp.rss;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.feed.AbstractRssFeedView;

import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Content;
import com.sun.syndication.feed.rss.Item;

public class RSSViewer extends AbstractRssFeedView{

	
	@Override
	protected void buildFeedMetadata(Map<String, Object> model, Channel feed,
		HttpServletRequest request) {
 
		feed.setTitle("Newest news from four feeds. Chronological ordering, newest up.");
		feed.setDescription("Four Kauppalehti news feeds merged and sorted by newest news up");
		feed.setLink("http://localhost:8080/rss");
 
		super.buildFeedMetadata(model, feed, request);
	}
	
	@Override
	protected List<Item> buildFeedItems(Map<String, Object> model,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
 
		@SuppressWarnings("unchecked")
		List<ContentModel> listContent = (List<ContentModel>) model.get("feedContent");
		List<Item> items = new ArrayList<Item>(listContent.size());
 
		for(ContentModel tempContent : listContent ){
 
			Item item = new Item();
 
			Content content = new Content();
			content.setValue(tempContent.getData());
			item.setContent(content);
 
			item.setTitle(tempContent.getTitle());
			item.setLink(tempContent.getUrl());
			item.setPubDate(tempContent.getNewsDate());
 
			items.add(item);
		}
 
		return items;
	}

}
