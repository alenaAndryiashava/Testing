package com.telran.otto.steps;

import com.telran.otto.pages.Gallery;
import io.cucumber.java8.En;

import static com.codeborne.selenide.Selenide.page;

public class GallerySteps implements En {
   Gallery gallery;

    public GallerySteps() {

        When("we press image from selected product on a gallery", () -> {
            gallery = page(Gallery.class);
            gallery.goToProductPage();
        });
    }
}
