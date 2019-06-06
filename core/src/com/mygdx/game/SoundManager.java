package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundManager
{
    private static SoundManager instance;

    public static float sfxVolume, musicVolume;
    public static Sound click, badCard1, badCard2, goodCard1, goodCard2, mainTheme1, mainTheme2, mainMenu, menuClick,
            error;

    private SoundManager()
    {
        sfxVolume = musicVolume = 1f;
        click = Gdx.audio.newSound(Gdx.files.internal("Sounds/Click_1.wav"));
        badCard1 = Gdx.audio.newSound(Gdx.files.internal("Sounds/Chainsaw_1.wav"));
        badCard2 = Gdx.audio.newSound(Gdx.files.internal("Sounds/Chainsaw_2.wav"));
        goodCard1 = Gdx.audio.newSound(Gdx.files.internal("Sounds/Birds_1.wav"));
        goodCard2 = Gdx.audio.newSound(Gdx.files.internal("Sounds/Birds_2.wav"));
        mainTheme1 = Gdx.audio.newSound(Gdx.files.internal("Sounds/MainTheme_1.wav"));
        mainTheme2 = Gdx.audio.newSound(Gdx.files.internal("Sounds/MainTheme_2.wav"));
        mainMenu = Gdx.audio.newSound(Gdx.files.internal("Sounds/MainMenu_1.wav"));
        menuClick = Gdx.audio.newSound(Gdx.files.internal("Sounds/YouGotThatClick.wav"));
        error = Gdx.audio.newSound(Gdx.files.internal("Sounds/ErrorSFX.wav"));
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
