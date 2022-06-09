package com.lucasbezerra.oddsproject.repository;

import com.lucasbezerra.oddsproject.model.dto.MatchesPayloadDTO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MatchRepositoryCustomImpl implements MatchRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<MatchesPayloadDTO> searchMatches(Map<String, Object> searchFilter, int offset, int pageSize) {
        StringBuilder matchQuery = new StringBuilder("select mt.id, ss.id as season_id, sg.description, sg.group_key,\n" +
                "th.name as team_home, ta.name as team_away, mt.score_home, mt.score_away, mt.round, mt.match_date, mt.processed\n" +
                "from matches mt \n" +
                "inner join season_group sg on sg.id = mt.group_id\n" +
                "inner join season ss on ss.id = sg.season_id\n" +
                "inner join team th on mt.team_home = th.id\n" +
                "inner join team ta on mt.team_away = ta.id");

        int lastIndex = searchFilter.size() - 1;
        int currentIdex = 0;

        StringBuilder criteriaBuilder = new StringBuilder(" where ");
        for (Map.Entry entry : searchFilter.entrySet()) {
             String criteria =  currentIdex < lastIndex ? entry.getKey() + " = ? and " : entry.getKey() + " = ?";
             criteriaBuilder.append(criteria);
             currentIdex++;
        }

        matchQuery.append(criteriaBuilder);
        matchQuery.append(" limit ?,?");

        Query query = entityManager.createNativeQuery(matchQuery.toString());

        currentIdex = 1;
        for (String key : searchFilter.keySet()) {
            query.setParameter(currentIdex, searchFilter.get(key));
            currentIdex++;
        }

        query.setParameter(currentIdex, offset);

        currentIdex++;
        query.setParameter(currentIdex, pageSize);


        List<MatchesPayloadDTO> resultSet = new ArrayList<>();
        for (Object row : query.getResultList()) {
            resultSet.add(new MatchesPayloadDTO((Object[]) row));
        }
        return resultSet;
    }
}
