package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundManager
{
    private static SoundManager instance;

    public Sound cardUsed,addPolution;

    private SoundManager()
    {
        cardUsed= Gdx.audio.newSound(Gdx.files.internal("Jump.wav"));
        //addPolution= Gdx.audio.newSound(Gdx.files.internal("Tonto.wav"));
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
