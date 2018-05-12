package com.augmentify.DataModule.Objects;

public interface DataObject
{
	public static enum Status {
        READ_OK,
        CREATE_OK,
		LOADING,
		ERROR,
        ERROR_NETWORK,
        ERROR_AUTHENTICATION,
		START
	};

	public void refresh();
	public void read();
    public void create();
    public void delete();

    public void onStatusChange(Status status);
    public void changeStatus(Status status);
}
