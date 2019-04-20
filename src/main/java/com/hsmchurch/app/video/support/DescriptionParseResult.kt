package com.hsmchurch.app.video.support

import com.hsmchurch.app.video.domain.BibleContent
import java.time.LocalDate

data class DescriptionParseResult(val filmedAt: LocalDate,
                                  val preacher: String,
                                  val bibleContents: List<BibleContent>)