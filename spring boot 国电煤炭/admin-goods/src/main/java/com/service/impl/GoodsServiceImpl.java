package com.service.impl;

import com.dao.GoodsDao;
import com.entity.Goods;
import com.github.pagehelper.PageHelper;
import com.service.GoodsService;
import com.utils.CamelUnderlineUtil;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 管理员商品管理Service
 * @Author Harlan
 * @Date 2020/10/29
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private RestHighLevelClient restClient;

    @Override
    public List<Goods> findAllGoods(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return goodsDao.findAllGoods();
    }

    @Override
    public List<Goods> findGoodsByState(int state, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return goodsDao.findGoodsByState(state);
    }

    @Override
    public Goods findGoodsById(int goodId) {
        return goodsDao.findGoodsById(goodId);
    }

    @Override
    public List<Map<String, Object>> searchGoodsByKeyWord(String keyWord) throws IOException {
        //指定查询索引请求
        SearchRequest searchRequest = new SearchRequest("goods");
        //创建查询条件
        SearchSourceBuilder searchSource = new SearchSourceBuilder();
        //创建多fields查询
        MultiMatchQueryBuilder multiMatchQuery = new MultiMatchQueryBuilder(keyWord);
        //构建条件
        searchSource.query(multiMatchQuery);
        //创建高亮结果
        HighlightBuilder highlight = new HighlightBuilder();
        highlight.preTags("<span style='color:red'>");
        highlight.postTags("</span>");
        //高亮字段
        highlight.field("user_id").field("info_type").field("goods_type").field("t_type").field("goods_number")
        .field("goods_price").field("goods_state").field("goods_date").field("goods_place").field("goods_deli")
        .field("goods_deli_type").field("goods_linkman").field("goods_tel").field("goods_remark");
        highlight.requireFieldMatch(false);
        searchSource.highlighter(highlight);
        //存入查询请求
        searchRequest.source(searchSource);
        //执行请求并接收响应
        SearchResponse response = restClient.search(searchRequest, RequestOptions.DEFAULT);
        //处理响应结果
        ArrayList<Map<String, Object>> resultList = new ArrayList<>();
        for (SearchHit hit : response.getHits().getHits()) {
            //获取索引id并添加到结果集
            String goodId = hit.getId();
            Map<String, Object> sourceMap = hit.getSourceAsMap();
            sourceMap.put("goods_id", goodId);
            //解析高亮字段
            Map<String, HighlightField> highlightFieldMap = hit.getHighlightFields();
            for (String s : highlightFieldMap.keySet()) {
                HighlightField field = highlightFieldMap.get(s);
                Text[] fragments = field.fragments();
                StringBuilder newFragment = new StringBuilder();
                for (Text fragment : fragments) {
                    newFragment.append(fragment);
                }
                sourceMap.put(s, newFragment.toString());
            }
            resultList.add(CamelUnderlineUtil.underLineToCamelForMap(sourceMap));
        }
        return resultList;
    }

    @Override
    public List<Goods> findGoodsByType(String goodsType, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return goodsDao.findGoodsByType(goodsType);
    }

    @Override
    public Goods changeGoodsStateById(Integer goodsId, Integer goodsState) {
        goodsDao.changeGoodsStateById(goodsId, goodsState);
        return goodsDao.findGoodsById(goodsId);
    }
}
