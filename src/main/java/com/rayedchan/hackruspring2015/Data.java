/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rayedchan.hackruspring2015;

import java.util.List;

/**
 *
 * @author rayedchan
 */
public class Data
{
    private List<Language> languages;
    
    public List<Language> getLanguages()
    {
        return this.languages;
    }
    public void setLanguages(List<Language> languages)
    {
        this.languages = languages;
    }
    
    @Override
    public String toString()
    {
        return String.format("Languages: %s", this.languages);
    }
}
