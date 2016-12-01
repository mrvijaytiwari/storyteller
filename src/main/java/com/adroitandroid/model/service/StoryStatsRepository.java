package com.adroitandroid.model.service;

import com.adroitandroid.model.StoryStats;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by pv on 01/12/16.
 */
interface StoryStatsRepository extends CrudRepository<StoryStats, Long> {
    @Modifying
    @Query(nativeQuery = true, value = "insert into story_stats(story_id, num_completed) values(?1, 1) on duplicate key update num_completed = num_completed + 1")
    void insertCompletedCountOnDuplicateKeyIncrement(Long storyId);
}
