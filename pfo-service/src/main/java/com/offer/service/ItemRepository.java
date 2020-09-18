package com.offer.service;

import com.offer.pojo.PfoPost;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ItemRepository extends ElasticsearchRepository<PfoPost,Long> {

}
