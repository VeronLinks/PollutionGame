package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundManager
{
    private static SoundManager instance;

    public Sound s;

    private SoundManager()
    {
        s= Gdx.audio.newSound(Gdx.files.internal("Jump.wav"));
    }

    public static SoundManager getInstance()
    {
        if (instance == null)
        {
            instance = new SoundManager();
        }
        return instance;
    }
}
