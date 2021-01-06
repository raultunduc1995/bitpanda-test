class ContactsComponent {
    /**
     * in the live code this is set after the view loaded; you can assume that this won't be null or empty
     * you can mock or change this if you want
     */
    var contacts: List<Contact>? = null

    /**
     * todo : returned list must
     *  1. hold only unique entries (data NOT id)
     *  2. hold max three entries
     *  3. sorted by "last_used" (if you use a custom sort, i'd suggest to use the unix timestamp)
     */
    fun getRecentContacts(): List<Contact> =
        contacts?.sortedByDescending { it.last_used.unix }
            ?.distinctBy { it.data }
            ?.take(3)
            ?: emptyList()
}
