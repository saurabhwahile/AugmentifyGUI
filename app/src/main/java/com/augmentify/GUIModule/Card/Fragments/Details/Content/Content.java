package com.augmentify.GUIModule.Card.Fragments.Details.Content;

import com.augmentify.DataModule.Objects.DataObject;

public interface Content
{
	public static enum CardType{
		EVENT,
		SAY
	};
	public void build();
    public void refresh(DataObject dataObject);
	public CardType getCardType();
}
