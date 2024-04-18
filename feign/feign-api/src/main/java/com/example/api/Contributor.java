package com.example.api;

import feign.Feign;
import feign.Param;
import feign.RequestLine;
import feign.codec.Decoder;

import java.util.List;

interface GitHub {
  @RequestLine("GET /repos/{owner}/{repo}/contributors")
  List<Contributor> contributors(@Param("owner") String owner, @Param("repo") String repo);
}

public class Contributor {
  String login;
  int contributions;

  public static void main(String... args) {
    GitHub github = Feign.builder()
            .decoder(new Decoder.Default())
            .target(GitHub.class, "https://api.github.com");

    // Fetch and print a list of the contributors to this library.
    List<Contributor> contributors = github.contributors("OpenFeign", "feign");
    for (Contributor contributor : contributors) {
      System.out.println(contributor.login + " (" + contributor.contributions + ")");
    }
  }

}

