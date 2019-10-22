package fr.raksrinana.nameascreated.extractor;

import com.drew.metadata.Directory;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com)
 *
 * @author Thomas Couchoud
 * @since 2018-09-30
 */
public class SimpleDateExtractor<T extends Directory> implements DateExtractor<T>{
	private final int tag;
	private final Class<T> klass;
	
	/**
	 * Constructor.
	 *
	 * @param klass The class of the directory.
	 * @param tag   The tag to fetch the date from.
	 */
	public SimpleDateExtractor(final Class<T> klass, final int tag){
		this.klass = klass;
		this.tag = tag;
	}
	
	@Override
	public ZonedDateTime parse(final Directory directory, final TimeZone tz){
		return Optional.ofNullable(directory.getDate(this.tag, tz)).map(Date::toInstant).map(date -> date.atZone(tz.toZoneId())).orElse(null);
	}
	
	@Override
	public Class<T> getKlass(){
		return klass;
	}
}