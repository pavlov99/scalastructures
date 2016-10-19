# Contributing

The following is a set of guidelines for contributing to this repository, hosted in the Asia Miles Organization on GitHub. These are just guidelines, not rules, use your best judgment and feel free to propose changes to this document in a pull request.

Contributors are advised to make pull requests to `develop` branch. This branch is used to create releases.

## Guidelines

Here is some advice on how to craft a pull request with the best possible
chance of being accepted.

### Tests

Bug fixes should include regression tests -- in the same commit as the fix.

If testing isn't feasible, the commit message should explain why.

New features and enhancements must be supported by a respectable test suite.

Some characteristics of good tests:

* includes comments: what is being tested and why?
* be minimal, deterministic, stable (unaffected by irrelevant changes), easy to understand and review
* have minimal dependencies: a compiler bug test should not depend on, e.g., the Scala library

### Documentation

This is of course required for new features and enhancements.

Any API additions should include Scaladoc.

Consider updating the package-level doc (in the package object), if appropriate.

### Coding standards

Please follow these standard code standards, though in moderation (scouts quickly learn to let sleeping dogs lie):

* Don't violate [DRY](http://programmer.97things.oreilly.com/wiki/index.php/Don%27t_Repeat_Yourself).
* Follow the [Boy Scout Rule](http://programmer.97things.oreilly.com/wiki/index.php/The_Boy_Scout_Rule).

Please also have a look at the [Scala Hacker Guide](http://www.scala-lang.org/contribute/hacker-guide.html) by @xeno-by.

### Clean commits, clean history

A pull request should consist of commits with messages that clearly state what problem the commit resolves and how.

Commit logs should be stated in the active, present tense.

A commit's subject should be 72 characters or less.  Overall, think of
the first line of the commit as a description of the action performed
by the commit on the code base, so use the active voice and the
present tense.  That also makes the commit subjects easy to reuse in
release notes.

If a commit purely refactors and is not intended to change behaviour,
say so.

    Here is standard advice on good commit messages:
    http://tbaggery.com/2008/04/19/a-note-about-git-commit-messages.html

### Pass CircleCI Bot

Our pull request bot automatically builds all the commits in a PR individually. (All, so we can `git bisect` later.)

Pull request has to pass tests and styleguides.

### Pass code review

Your PR will need to be assigned to one or more reviewers. You can suggest reviewers yourself.

A reviewer gives the green light by commenting "LGTM" (looks good to me).

A review feedback may be addressed by pushing new commits to the request, if these commits stand on their own.
