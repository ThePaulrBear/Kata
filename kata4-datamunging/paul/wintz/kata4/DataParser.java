package paul.wintz.kata4;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Optional;

public class DataParser<T> {

	private DelimitedStringSplitter splitter;
	private ObjectFromStringListMapper<T> wrangler;

	public DataParser(DelimitedStringSplitter splitter, ObjectFromStringListMapper<T> wrangler) {
		setSplitter(splitter);
		setWrangler(wrangler);
	}

	public Optional<T> parse(String line) {
		return wrangler.dataObjectFromList(splitter.split(line));
	}

	public final void setSplitter(DelimitedStringSplitter splitter) {
		this.splitter = checkNotNull(splitter);
	}

	public final void setWrangler(ObjectFromStringListMapper<T> wrangler) {
		this.wrangler = checkNotNull(wrangler);
	}

}