package com.nikitakrapo.progressif.profile

import com.nikitakrapo.progressif.auth.user.User
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BuildSectionsTest {

    @Test
    fun `with non-null user header displayName matches user displayName`() {
        val sections = buildSections(User(id = "id", username = "Alice"))
        val header = sections.filterIsInstance<ProfileSection.Header>().first()
        assertEquals("Alice", header.displayName)
    }

    @Test
    fun `with null user header has empty displayName`() {
        val sections = buildSections(null)
        val header = sections.filterIsInstance<ProfileSection.Header>().first()
        assertEquals("", header.displayName)
    }

    @Test
    fun `sections contain exactly one logout button`() {
        val sections = buildSections(User(id = "id", username = "Name"))
        val logoutButtons = sections
            .filterIsInstance<ProfileSection.Button>()
            .filter { it.intent is ProfileStore.Intent.ShowLogoutConfirmation }
        assertEquals(1, logoutButtons.size)
    }

    @Test
    fun `header appears before logout button`() {
        val sections = buildSections(User(id = "id", username = "Name"))
        val headerIndex = sections.indexOfFirst { it is ProfileSection.Header }
        val buttonIndex = sections.indexOfFirst { it is ProfileSection.Button }
        assertTrue(headerIndex < buttonIndex)
    }
}
