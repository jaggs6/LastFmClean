package uk.co.mgntech.last_fm_mvvm.ui

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.SmallTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uk.co.mgntech.last_fm_mvvm.R

@SmallTest
@RunWith(AndroidJUnit4::class)
class SearchActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SearchActivity::class.java)

    @Test
    fun searchActivityLaunchTest() {
        val linearLayout = onView(
            allOf(
                withContentDescription("Albums"),
                withParent(withParent(withId(R.id.tabs_search))),
                isDisplayed()
            )
        )
        linearLayout.check(matches(isDisplayed()))

        val linearLayout2 = onView(
            allOf(
                withContentDescription("Artists"),
                withParent(withParent(withId(R.id.tabs_search))),
                isDisplayed()
            )
        )
        linearLayout2.check(matches(isDisplayed()))

        val linearLayout3 = onView(
            allOf(
                withContentDescription("Songs"),
                withParent(withParent(withId(R.id.tabs_search))),
                isDisplayed()
            )
        )
        linearLayout3.check(matches(isDisplayed()))

        val textView = onView(
            allOf(
                withText("Type in the search box above to begin"),
                withParent(
                    allOf(
                        withId(R.id.empty_search),
                        withParent(withId(R.id.cl_search))
                    )
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Type in the search box above to begin")))

        val imageView = onView(
            allOf(
                withId(R.id.search_mag_icon),
                withParent(
                    allOf(
                        withId(R.id.search_edit_frame),
                        withParent(withId(R.id.search_bar))
                    )
                ),
                isDisplayed()
            )
        )
        imageView.check(matches(isDisplayed()))
    }

    @Test
    fun searchActivityTabTest() {
        val searchAutoComplete = onView(
            allOf(
                withId(R.id.search_src_text),
                childAtPosition(
                    allOf(
                        withId(R.id.search_plate),
                        childAtPosition(
                            withId(R.id.search_edit_frame),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        searchAutoComplete.perform(ViewActions.replaceText("love"), ViewActions.closeSoftKeyboard())

        val searchAutoComplete2 = onView(
            allOf(
                withId(R.id.search_src_text), withText("love"),
                childAtPosition(
                    allOf(
                        withId(R.id.search_plate),
                        childAtPosition(
                            withId(R.id.search_edit_frame),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        searchAutoComplete2.perform(ViewActions.pressImeActionButton())

        Thread.sleep(5000)

        val textView = onView(
            allOf(
                withId(R.id.cv_text_name), withText("LOVE YOURSELF 結 'Answer'"),
                withParent(withParent(withId(R.id.cv_search))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("LOVE YOURSELF 結 'Answer'")))

        val textView2 = onView(
            allOf(
                withId(R.id.cv_text_additional_name), withText("\uD83C\uDF99️BTS"),
                withParent(withParent(withId(R.id.cv_search))),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("\uD83C\uDF99️BTS")))

        val imageView = onView(
            allOf(
                withId(R.id.cv_image), withContentDescription("LOVE YOURSELF 結 'Answer'"),
                withParent(withParent(withId(R.id.cv_search))),
                isDisplayed()
            )
        )
        imageView.check(matches(isDisplayed()))

        val tabView = onView(
            allOf(
                withContentDescription("Artists"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tabs_search),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        tabView.perform(ViewActions.click())

        val textView3 = onView(
            allOf(
                withId(R.id.cv_text_name), withText("Love"),
                withParent(withParent(withId(R.id.cv_search))),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("Love")))

        val imageView2 = onView(
            allOf(
                withId(R.id.cv_image), withContentDescription("Love"),
                withParent(withParent(withId(R.id.cv_search))),
                isDisplayed()
            )
        )
        imageView2.check(matches(isDisplayed()))

        val tabView2 = onView(
            allOf(
                withContentDescription("Songs"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tabs_search),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        tabView2.perform(ViewActions.click())

        val textView4 = onView(
            allOf(
                withId(R.id.cv_text_name), withText("FAKE LOVE"),
                withParent(withParent(withId(R.id.cv_search))),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("FAKE LOVE")))

        val textView5 = onView(
            allOf(
                withId(R.id.cv_text_additional_name), withText("\uD83C\uDF99️BTS"),
                withParent(withParent(withId(R.id.cv_search))),
                isDisplayed()
            )
        )
        textView5.check(matches(withText("\uD83C\uDF99️BTS")))

        val textView6 = onView(
            allOf(
                withId(R.id.cv_text_listeners), withText("\uD83C\uDFA7133346"),
                withParent(withParent(withId(R.id.cv_search))),
                isDisplayed()
            )
        )
        textView6.check(matches(isDisplayed()))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>,
        position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent) &&
                        view == parent.getChildAt(position)
            }
        }
    }
}
