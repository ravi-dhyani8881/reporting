package com.mobicart.dao;

import java.util.List;

import com.mobicart.dto.ColorSchemeDto;
import org.springframework.dao.DataAccessException;
import com.mobicart.model.AppColorScheme;
import com.mobicart.model.ColorScheme;

public interface ColorSchemeDAO {
	
	/**
	 * Find all default colorschemes
	 * @return List of {@link ColorScheme} instances
	 */
	public List<ColorScheme> findAllSchemes();
	
	/**
	 * Find colorscheme for an app Id
	 * @param appId
	 * @return {@link ColorScheme} instance
	 */
	public ColorScheme findColorScheme(Long appId);
	/**
	 * Find colorscheme for an app Id
	 * @param appId
	 * @return {@link ColorScheme} instance
	 */
	public ColorSchemeDto findColorSchemeEnhanced(Long appId);
	/**
	 * Save app colorschme
	 * @param appColorScheme
	 * @return true or false
	 */
	public boolean saveAppColorScheme(AppColorScheme appColorScheme) ;
	
	/**
	 * update app color shcme
	 * @param appColorScheme
	 * @return true or false
	 */
	public boolean updateAppColorScheme(AppColorScheme appColorScheme);
	
	/**
	 * Save new color scheme
	 * @param colorscheme
	 * @param appid
	 * @return {@link ColorScheme} id
	 */
	public long saveColorScheme(ColorScheme colorscheme,long appid);
	
	/**
	 * Delete color schems
	 * @param colorschemeId
	 * @return 0 or 1
	 */
	public int deleteColorScheme(Long colorschemeId);
	
	
	/**
	 * Find colorscheme id
	 * @param appId
	 * @return Long id
	 */
	public Long findExistingColorScheme(Long appId);

}
