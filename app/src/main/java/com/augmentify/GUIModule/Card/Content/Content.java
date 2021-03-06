package com.augmentify.GUIModule.Card.Content;

import com.augmentify.DataModule.Objects.DataObject;

public interface Content
{
	public static enum CardType{
		EVENT,
		SAY,
        PROFILE,
        REQUEST,
        NOTIFICATION
	};
	public void build();
    public void refresh(DataObject dataObject);
	public CardType getCardType();
}
