package com.edward.assigment.model;

public class ExpandableChild {
    public ExpandableChild(String _text, int _icon) {
        this._text = _text;
        this._icon = _icon;
    }

    public String get_text() {
        return _text;
    }

    public void set_text(String _text) {
        this._text = _text;
    }

    public int get_icon() {
        return _icon;
    }

    public void set_icon(int _icon) {
        this._icon = _icon;
    }

    private String _text;
    private int _icon;
}
