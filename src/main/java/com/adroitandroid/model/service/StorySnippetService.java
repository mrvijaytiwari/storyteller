package com.adroitandroid.model.service;

import com.adroitandroid.model.StorySnippet;
import com.adroitandroid.model.UserSnippetVote;
import com.fasterxml.jackson.databind.node.ArrayNode;

/**
 * Created by pv on 25/10/16.
 */
public interface StorySnippetService {

    ArrayNode getAllSnippetsForPrompt(long promptId);

    ArrayNode getAllSnippetsByUserOnActivePrompts(long userId);

    ArrayNode getAllSnippetsByUser(Long userId, boolean activePrompts);

    StorySnippet addSnippet(StorySnippet snippet);

    void addUserVote(UserSnippetVote vote);
}
