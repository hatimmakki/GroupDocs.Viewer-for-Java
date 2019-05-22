package com.groupdocs.viewer.examples;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.groupdocs.viewer.config.ViewerConfig;
import com.groupdocs.viewer.converter.options.EmailField;
import com.groupdocs.viewer.converter.options.HtmlOptions;
import com.groupdocs.viewer.converter.options.ImageOptions;
import com.groupdocs.viewer.converter.options.ImageQuality;
import com.groupdocs.viewer.converter.options.PageSize;
import com.groupdocs.viewer.converter.options.TextOverflowMode;
import com.groupdocs.viewer.converter.options.TimeUnit;
import com.groupdocs.viewer.domain.Attachment;
import com.groupdocs.viewer.domain.AttachmentBase;
import com.groupdocs.viewer.domain.FileDescription;
import com.groupdocs.viewer.domain.PageData;
import com.groupdocs.viewer.domain.Transformation;
import com.groupdocs.viewer.domain.Watermark;
import com.groupdocs.viewer.domain.WatermarkPosition;
import com.groupdocs.viewer.domain.WindowsAuthenticationCredential;
import com.groupdocs.viewer.domain.containers.CadDocumentInfoContainer;
import com.groupdocs.viewer.domain.containers.DocumentFormatsContainer;
import com.groupdocs.viewer.domain.containers.DocumentInfoContainer;
import com.groupdocs.viewer.domain.containers.FileContainer;
import com.groupdocs.viewer.domain.containers.FileListContainer;
import com.groupdocs.viewer.domain.containers.PdfDocumentInfoContainer;
import com.groupdocs.viewer.domain.containers.PrintableHtmlContainer;
import com.groupdocs.viewer.domain.containers.ProjectDocumentInfoContainer;
import com.groupdocs.viewer.domain.html.HtmlResource;
import com.groupdocs.viewer.domain.html.PageHtml;
import com.groupdocs.viewer.domain.image.PageImage;
import com.groupdocs.viewer.domain.options.DocumentInfoOptions;
import com.groupdocs.viewer.domain.options.FileListOptions;
import com.groupdocs.viewer.domain.options.PdfFileOptions;
import com.groupdocs.viewer.domain.options.PrintableHtmlOptions;
import com.groupdocs.viewer.domain.options.ReorderPageOptions;
import com.groupdocs.viewer.domain.options.RotatePageOptions;
import com.groupdocs.viewer.handler.ViewerHtmlHandler;
import com.groupdocs.viewer.handler.ViewerImageHandler;
import com.groupdocs.viewer.handler.input.IInputDataHandler;
import com.groupdocs.viewer.internal.c.a.e.system.DateTime;
import com.groupdocs.viewer.internal.c.a.ms.System.IO.Path;
import com.groupdocs.viewer.utils.CultureInfo;

public class ViewGenerator {
	/**
	 * Fetches complete information of the email attachment
	 * 
	 * @param emailFile
	 *            name of the source email document
	 * @throws Throwable
	 */
	public static void renderAttachmentsInformation(String emailFile) {
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig viewerConfig = Utilities.getConfiguration();

			// Init viewer html handler
			ViewerImageHandler handler = new ViewerImageHandler(viewerConfig);

			DocumentInfoOptions options = new DocumentInfoOptions();
			DocumentInfoContainer documentInfo = handler.getDocumentInfo(emailFile, options);

			System.out.println("DateCreated: " + documentInfo.getDateCreated());
			System.out.println("DocumentType: " + documentInfo.getDocumentType());
			System.out.println("DocumentTypeFormat: " + documentInfo.getDocumentTypeFormat());
			System.out.println("Extension: " + documentInfo.getExtension());
			System.out.println("FileType: " + documentInfo.getFileType());
			System.out.println("Guid: " + documentInfo.getGuid());
			System.out.println("LastModificationDate: " + documentInfo.getLastModificationDate());
			System.out.println("Name: " + documentInfo.getName());
			System.out.println("PageCount: " + documentInfo.getPages().size());
			System.out.println("Size: " + documentInfo.getSize());

			// Iterate over the attachments collection
			for (AttachmentBase attachment : documentInfo.getAttachments()) {
				System.out.println("\n--------------------");
				System.out.println("Attach name: " + attachment.getName());
				// Get number of pages in attachment
				List<PageImage> pages = handler.getPages(attachment);
				System.out.println("Number of pages in attachment: " + pages.size());
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
	}

	/**
	 * Fetches complete information of the source document by guid
	 * 
	 * @param guid
	 *            guid of the source document
	 */
	public static void renderDocumentInformationByGuid(String guid) {
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Create html handler
			ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);

			// Get document information
			DocumentInfoOptions options = new DocumentInfoOptions();
			DocumentInfoContainer documentInfo = htmlHandler.getDocumentInfo(guid, options);
			System.out.println("DateCreated: " + documentInfo.getDateCreated());
			System.out.println("DocumentType: " + documentInfo.getDocumentType());
			System.out.println("DocumentTypeFormat: " + documentInfo.getDocumentTypeFormat());
			System.out.println("Extension: " + documentInfo.getExtension());
			System.out.println("FileType: " + documentInfo.getFileType());
			System.out.println("Guid: " + documentInfo.getGuid());
			System.out.println("LastModificationDate: " + documentInfo.getLastModificationDate());
			System.out.println("Name: " + documentInfo.getName());
			System.out.println("PageCount: " + documentInfo.getPages().size());
			System.out.println("Size: " + documentInfo.getSize());

			for (PageData pageData : documentInfo.getPages()) {
				System.out.println("Page number: " + pageData.getNumber());
				System.out.println("Page name: " + pageData.getName());
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
	}

	/**
	 * Fetches complete information of the source document by stream
	 * 
	 * @param fileName
	 *            Name of the source document
	 */
	public static void renderDocumentInformationByStream(String fileName) {
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Create html or image handler
			ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);

			// Get document stream
			FileInputStream stream = new FileInputStream("./Data/Storage/" + fileName);
			// Get document information
			DocumentInfoOptions options = new DocumentInfoOptions();
			DocumentInfoContainer documentInfo = htmlHandler.getDocumentInfo(stream, options);

			System.out.println("DateCreated: " + documentInfo.getDateCreated());
			System.out.println("DocumentType: " + documentInfo.getDocumentType());
			System.out.println("DocumentTypeFormat: " + documentInfo.getDocumentTypeFormat());
			System.out.println("Extension: " + documentInfo.getExtension());
			System.out.println("FileType: " + documentInfo.getFileType());
			System.out.println("Guid: " + documentInfo.getGuid());
			System.out.println("LastModificationDate: " + documentInfo.getLastModificationDate());
			System.out.println("Name: " + documentInfo.getName());
			System.out.println("PageCount: " + documentInfo.getPages().size());
			System.out.println("Size: " + documentInfo.getSize());

			for (PageData pageData : documentInfo.getPages()) {
				System.out.println("Page number: " + pageData.getNumber());
				System.out.println("Page name: " + pageData.getName());
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
	}

	/**
	 * Fetches complete information of the source document by stream and
	 * document name
	 * 
	 * @param fileName
	 *            Name of the source document
	 */
	public static void renderDocumentInformationByStreamAndDocumentName(String fileName) {
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Create html handler
			ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);

			// Get document stream
			FileInputStream stream = new FileInputStream("./Data/Storage/" + fileName);
			// Get document information
			DocumentInfoOptions options = new DocumentInfoOptions();
			DocumentInfoContainer documentInfo = htmlHandler.getDocumentInfo(stream, fileName, options);

			System.out.println("DateCreated: " + documentInfo.getDateCreated());
			System.out.println("DocumentType: " + documentInfo.getDocumentType());
			System.out.println("DocumentTypeFormat: " + documentInfo.getDocumentTypeFormat());
			System.out.println("Extension: " + documentInfo.getExtension());
			System.out.println("FileType: " + documentInfo.getFileType());
			System.out.println("Guid: " + documentInfo.getGuid());
			System.out.println("LastModificationDate: " + documentInfo.getLastModificationDate());
			System.out.println("Name: " + documentInfo.getName());
			System.out.println("PageCount: " + documentInfo.getPages().size());
			System.out.println("Size: " + documentInfo.getSize());

			for (PageData pageData : documentInfo.getPages()) {
				System.out.println("Page number: " + pageData.getNumber());
				System.out.println("Page name: " + pageData.getName());
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
	}

	/**
	 * Fetches complete information of the source document by uri
	 * 
	 * @param uri
	 *            Uri of the source document
	 */
	public static void renderDocumentInformationByUri(String URL) {
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Create html handler
			ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);

			URI uri = new URI(URL);

			// Get document information
			DocumentInfoOptions options = new DocumentInfoOptions();
			DocumentInfoContainer documentInfo = htmlHandler.getDocumentInfo(uri, options);

			System.out.println("DateCreated: " + documentInfo.getDateCreated());
			System.out.println("DocumentType: " + documentInfo.getDocumentType());
			System.out.println("DocumentTypeFormat: " + documentInfo.getDocumentTypeFormat());
			System.out.println("Extension: " + documentInfo.getExtension());
			System.out.println("FileType: " + documentInfo.getFileType());
			System.out.println("Guid: " + documentInfo.getGuid());
			System.out.println("LastModificationDate: " + documentInfo.getLastModificationDate());
			System.out.println("Name: " + documentInfo.getName());
			System.out.println("PageCount: " + documentInfo.getPages().size());
			System.out.println("Size: " + documentInfo.getSize());

			for (PageData pageData : documentInfo.getPages()) {
				System.out.println("Page number: " + pageData.getNumber());
				System.out.println("Page name: " + pageData.getName());
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
	}

	/**
	 * Fetches complete information of the source document by uri with Windows
	 * Authentication Credential.
	 * 
	 * @param uri
	 *            Uri of the source document
	 * @param userName
	 *            userName for Windows Authentication Credential.
	 * @param passWord
	 *            password for Windows Authentication Credential.
	 */
	public static void renderDocumentInformationByUriWithWindowsAuthenticationCredential(String URL, String userName,
			String password) {
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Create html handler
			ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);

			URI uri = new URI(URL);
			WindowsAuthenticationCredential credential = new WindowsAuthenticationCredential(userName, password);
			// Get document information
			DocumentInfoOptions options = new DocumentInfoOptions();
			DocumentInfoContainer documentInfo = htmlHandler.getDocumentInfo(uri, credential, options);

			System.out.println("DateCreated: " + documentInfo.getDateCreated());
			System.out.println("DocumentType: " + documentInfo.getDocumentType());
			System.out.println("DocumentTypeFormat: " + documentInfo.getDocumentTypeFormat());
			System.out.println("Extension: " + documentInfo.getExtension());
			System.out.println("FileType: " + documentInfo.getFileType());
			System.out.println("Guid: " + documentInfo.getGuid());
			System.out.println("LastModificationDate: " + documentInfo.getLastModificationDate());
			System.out.println("Name: " + documentInfo.getName());
			System.out.println("PageCount: " + documentInfo.getPages().size());
			System.out.println("Size: " + documentInfo.getSize());

			for (PageData pageData : documentInfo.getPages()) {
				System.out.println("Page number: " + pageData.getNumber());
				System.out.println("Page name: " + pageData.getName());
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
	}

	/**
	 * Gets image representation of the document
	 * 
	 * @param fileName
	 *            name of the source document
	 */
	public static void renderDocumentAsImage(String fileName) {
		// ExStart:GetDocumentImageRepresentation
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Create image handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);
			String guid = fileName;

			// Get pages
			List<PageImage> pages = imageHandler.getPages(guid);

			// Save pages
			for (PageImage page : pages) {
				Utilities.saveAsImage(page.getPageNumber() + "_" + fileName, "png", page.getStream());
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:GetDocumentImageRepresentation
	}

	/**
	 * Gets Image representation of the attached documents with an email file
	 * 
	 * @param emailFile
	 *            name of the source email file which contains attachments
	 */
	public static void renderDocumentAsImageFromEmailAttachment(String emailFile) {
		// ExStart:GetImageRepresentationOfTheAttachment

		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig viewerConfig = Utilities.getConfiguration();

			// Init viewer image handler
			ViewerImageHandler handler = new ViewerImageHandler(viewerConfig);

			// Clear files from cache
			// handler.clearCache();

			DocumentInfoContainer info = handler.getDocumentInfo(emailFile);
			// Iterate over the attachments collection
			for (AttachmentBase attachment : info.getAttachments()) {

				List<PageImage> pages = handler.getPages(attachment);
				for (PageImage page : pages) {
					Utilities.saveAsImage(page.getPageNumber() + "_" + attachment.getName(), "png", page.getStream());
				}
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:GetImageRepresentationOfTheAttachment
	}

	/**
	 * Gets image representation of the N consecutive documents
	 * 
	 * @param fileName
	 *            name of the source document
	 * @param pageNumber
	 *            starting page number
	 * @param countPagesToConvert
	 *            total number of pages to convert
	 */
	public static void renderDocumentAsImage(String fileName, int pageNumber, int countPagesToConvert) {
		// ExStart:GetDocumentImageRepresentationOfNConsecutiveDocument
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Create image handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);
			String guid = fileName;

			// Options to convert countPagesToConvert consecutive pages starting
			// from pageNumber
			ImageOptions options = new ImageOptions();
			options.setPageNumber(pageNumber);
			options.setCountPagesToRender(countPagesToConvert);

			// Get pages
			List<PageImage> pages = imageHandler.getPages(guid, options);

			// Save pages
			for (PageImage page : pages) {
				Utilities.saveAsImage(page.getPageNumber() + "_" + fileName, "png", page.getStream());
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:GetDocumentImageRepresentationOfNConsecutiveDocument
	}

	/**
	 * Gets image representation of provided page numbers
	 * 
	 * @param fileName
	 *            name of the source document
	 * @param listPagesToConvert
	 *            list of pages to render
	 */
	public static void renderDocumentAsImage(String fileName, List<Integer> listPagesToConvert) {
		// ExStart:GetImageRepresentationForCustomPages
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();
			// Create image handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);
			String guid = fileName;

			// Options to convert 1, 3, 5, 6, 8 page numbers
			ImageOptions options = new ImageOptions();
			options.setPageNumbersToRender(listPagesToConvert);

			List<PageImage> pages = imageHandler.getPages(guid, options);

			for (PageImage page : pages) {
				Utilities.saveAsImage(page.getPageNumber() + "_" + fileName, "png", page.getStream());
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:GetImageRepresentationForCustomPages
	}

	/**
	 * Gets image representation of the document with rotation of provided angle
	 * 
	 * @param fileName
	 *            name of the source document
	 * @param rotationAngle
	 *            value of angle
	 */
	public static void renderDocumentAsImage(String fileName, int rotationAngle) {
		// ExStart:RotatePageToNintyDegreeInImageMode
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();
			// Create image handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);
			String guid = fileName;

			// Set rotation angle for page number 3
			RotatePageOptions rotateOptions = new RotatePageOptions(3, rotationAngle);

			// Perform page rotation
			imageHandler.rotatePage(guid, rotateOptions);

			// Set image options to include Rotate transformations
			ImageOptions imageOptions = new ImageOptions();
			imageOptions.setTransformations(Transformation.Rotate);

			List<PageImage> pages = imageHandler.getPages(guid, imageOptions);

			for (PageImage page : pages) {
				Utilities.saveAsImage(page.getPageNumber() + "_" + fileName, "png", page.getStream());
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:RotatePageToNintyDegreeInImageMode
	}

	/**
	 * Renders document to image excluding transformation
	 * 
	 * @param fileName
	 *            name of the source document
	 * @param excludeTransformation
	 *            excludeTransformation true or false
	 */
	public static void renderDocumentAsImage(String fileName, boolean excludeTransformation) {
		// ExStart:RetrieveAllImagesExcludingTransformation
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();
			// Create image handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);
			String guid = fileName;

			// Set image options NOT to include ANY transformations
			ImageOptions noTransformationsOptions = new ImageOptions();
			// This is set by default
			noTransformationsOptions.setTransformations(Transformation.None);

			// Get image representation of all document pages, without
			// transformations
			List<PageImage> pagesWithoutTransformations = imageHandler.getPages(guid, noTransformationsOptions);

			// Get image representation of all document pages, without
			// transformations
			List<PageImage> pagesWithoutTransformations2 = imageHandler.getPages(guid);
			for (PageImage page : pagesWithoutTransformations) {
				Utilities.saveAsImage(page.getPageNumber() + "_" + fileName, "png", page.getStream());
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:RetrieveAllImagesExcludingTransformation
	}

	/**
	 * Gets image representation of document with reorder
	 * 
	 * @param fileName
	 *            name of the source document
	 * @param pageNumber
	 *            page number that needs to be reordered
	 * @param newPosition
	 *            reorder to a new position
	 * @param isTransformation
	 *            isTransformation is true of false
	 */
	public static void renderDocumentAsImage(String fileName, int pageNumber, int newPosition,
			boolean isTransformation) {
		// ExStart:ReorderFirstAndSecondPagesInImageMode
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();
			// Create image handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);
			String guid = fileName;

			// Perform page reorder
			ReorderPageOptions options = new ReorderPageOptions(pageNumber, newPosition);
			imageHandler.reorderPage(guid, options);

			ImageOptions imageOptions = new ImageOptions();
			imageOptions.setTransformations(Transformation.Reorder);

			List<PageImage> pages = imageHandler.getPages(guid, imageOptions);
			int pageNumberCount = 1;
			for (PageImage page : pages) {
				System.out.println("\t\tPage number: " + page.getPageNumber());
				Utilities.saveAsImage(pageNumberCount + "_" + fileName, "png", page.getStream());
				pageNumberCount++;
			}

		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:ReorderFirstAndSecondPagesInImageMode
	}

	/**
	 * Gets image representation of the document with watermark
	 * 
	 * @param fileName
	 *            name of the source document
	 * @param watermarkText
	 *            text that will appear on the image as watermark
	 */
	public static void renderDocumentAsImage(String fileName, String watermarkText) {
		// ExStart:AddWatermarkToImagePageRepresentation
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Create image handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);
			String guid = fileName;

			ImageOptions options = new ImageOptions();

			// Set watermark properties
			Watermark watermark = new Watermark(watermarkText);
			// Set color for watermark with values Red, Green, Blue and
			// Alpha(Transparency)
			Color watermarkColor = new Color(200, 75, 75, 100);
			watermark.setColor(watermarkColor);
			watermark.setPosition(WatermarkPosition.Diagonal);
			watermark.setWidth(100f);

			// Set watermark font name which contains Japanese characters
			watermark.setFontName("MS Gothic");

			options.setWatermark(watermark);

			// Get document pages image representation with watermark
			List<PageImage> pages = imageHandler.getPages(guid, options);
			for (PageImage page : pages) {
				Utilities.saveAsImage(page.getPageNumber() + "_" + fileName, "png", page.getStream());
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:AddWatermarkToImagePageRepresentation
	}

	/**
	 * Gets image representation of the document from relative path
	 * 
	 * @param fileName
	 *            name of the source document
	 */
	public static void renderDocumentAsImageFromRelativePath(String fileName) {
		// ExStart:GetDocumentRepresentationFromRelativePath
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();
			// Create image handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);

			// Set relative path. So that full path will be
			String guid = fileName;

			// Get pages by absolute path
			List<PageImage> pages = imageHandler.getPages(guid);
			for (PageImage page : pages) {
				Utilities.saveAsImage(page.getPageNumber() + "_" + fileName, "png", page.getStream());
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:GetDocumentRepresentationFromRelativePath
	}

	/**
	 * Gets image representation of the document from Uri
	 * 
	 * @param URL
	 *            url of the source document/file
	 */
	public static void renderDocumentAsImageFromUri(String URL) {
		// ExStart:GetDocumentRepresentationFromUri
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();
			// Create image handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);
			URI uri = new URI(URL);

			// Get pages by absolute path
			List<PageImage> pages = imageHandler.getPages(uri);
			for (PageImage page : pages) {
				Utilities.saveAsImage(Integer.toString(page.getPageNumber()), "png", page.getStream());
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:GetDocumentRepresentationFromUri
	}

	/**
	 * Gets image representation of the document from input stream
	 * 
	 * @param fileName
	 *            name of the source document
	 */
	public static void renderDocumentAsImageFromInputStream(String fileName) {
		// ExStart:GetDocumentRepresentationFromInputStream
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();
			// Create image handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);

			// Get file stream
			FileInputStream fileStream = new FileInputStream("./Data/Storage/word.docx");

			// Get pages
			List<PageImage> pages = imageHandler.getPages(fileStream,
					"./Data/Strage/tempfile." + Utilities.getFileExtension(fileName));
			for (PageImage page : pages) {
				Utilities.saveAsImage(page.getPageNumber() + "_" + fileName, "png", page.getStream());
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:GetDocumentRepresentationFromInputStream
	}

	/**
	 * Gets image representation of the document from absolute path
	 * 
	 * @param fileAbsolutePath
	 *            absolute path of the document
	 */
	public static void renderDocumentAsImageFromAbsolutePath(String fileAbsolutePath) {
		// ExStart:GetDocumentRepresentationFromAbsolutePath
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();
			// Create image handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);

			// Set absolute path to file
			String guid = fileAbsolutePath;

			// Get pages by absolute path
			List<PageImage> pages = imageHandler.getPages(guid);
			for (PageImage page : pages) {
				Utilities.saveAsImage(Integer.toString(page.getPageNumber()), "png", page.getStream());
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:GetDocumentRepresentationFromAbsolutePath
	}

	public static void renderDocumentAsImageMultipleTransformations(String fileName, int rotationAngle,
			int rotationPageNumber, int reorderPageNumber, int reorderNewPosition) {
		// ExStart:PerformMultipleTransformationsInImageMode
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();
			// Create image handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);
			String guid = fileName;
			RotatePageOptions rotatePage = new RotatePageOptions(rotationPageNumber, rotationAngle);
			// Rotate page
			imageHandler.rotatePage(guid, rotatePage);

			// Rotate second page 180 degrees
			// imageHandler.rotatePage(new RotatePageOptions(guid, 2, 180));
			ReorderPageOptions reorderPage = new ReorderPageOptions(reorderPageNumber, reorderNewPosition);
			// Reorder pages
			imageHandler.reorderPage(guid, reorderPage);

			// Set options to include rotate and reorder transformations
			ImageOptions options = new ImageOptions();
			options.setTransformations(Transformation.Rotate | Transformation.Reorder);

			// Set watermark properties
			Watermark watermark = new Watermark("This is watermark text");
			watermark.setColor(Color.BLUE);
			watermark.setPosition(WatermarkPosition.Diagonal);
			watermark.setWidth(100f);

			options.setWatermark(watermark);

			// Get document pages image representation with multiple
			// transformations
			List<PageImage> pages = imageHandler.getPages(guid, options);
			for (PageImage page : pages) {
				Utilities.saveAsImage(page.getPageNumber() + "_" + fileName, "png", page.getStream());
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:PerformMultipleTransformationsInImageMode
	}

	/**
	 * Gets html representation of the document with embedded resource
	 * 
	 * @param fileName
	 *            name of the source document
	 */
	public static void renderDocumentAsHtml(String fileName) {
		// ExStart:GetHtmlRepresentationWithEmbeddedRec
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Create html handler
			ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);
			String guid = fileName;

			HtmlOptions options = new HtmlOptions();
			options.setEmbedResources(true);

			List<PageHtml> pages = htmlHandler.getPages(guid, options);

			for (PageHtml page : pages) {
				Utilities.saveAsHtml(page.getPageNumber() + "_" + fileName, page.getHtmlContent());
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:GetHtmlRepresentationWithEmbeddedRec
	}

	/**
	 * Renders PDF document in a precise mode
	 * 
	 * @throws Throwable
	 */
	public static void renderPDFDocumentInPreciseMode(String fileName) throws Throwable {
		// ExStart:renderPDFDocumentInPreciseMode_17.5
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Create html handler
			ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);
			String guid = fileName;

			// Set pdf options to render content in a precise mode
			HtmlOptions options = new HtmlOptions();
			options.getPdfOptions().setEnablePreciseRendering(true); // Default
			// value
			// is
			// false

			// Get pages
			List<PageHtml> pages = htmlHandler.getPages(guid, options);

			for (PageHtml page : pages) {
				System.out.println("Page number: " + page.getPageNumber());
				System.out.println("Html content: " + page.getHtmlContent());
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:renderPDFDocumentInPreciseMode_17.5
	}

	/**
	 * Renders document as Html with comments
	 * 
	 * @throws Throwable
	 */
	public static void renderDocumentAsHtmlWithComments(String fileName) throws Throwable {
		// ExStart:renderDocumentAsHtmlWithComments_17.5
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Create HTML handler
			ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);
			String guid = fileName;

			// Set words options to render content with comments
			HtmlOptions options = new HtmlOptions();
			options.setRenderComments(true); // Default value is false

			// Get pages
			List<PageHtml> pages = htmlHandler.getPages(guid, options);

			for (PageHtml page : pages) {
				System.out.println("Page number: " + page.getPageNumber());
				final String htmlContent = page.getHtmlContent();
				System.out.println("Html content: " + htmlContent);
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:renderDocumentAsHtmlWithComments_17.5
	}

	/**
	 * Renders document as PDF with comments
	 * 
	 * @throws Throwable
	 */
	public static void renderDocumentAsPDFWithComments(String fileName) throws Throwable {
		// ExStart:renderDocumentAsPDFWithComments_17.5
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Create HTML handler
			ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);
			String guid = fileName;

			// Set PdfOptions to get Pdf file with comments
			PdfFileOptions pdfFileOptions = new PdfFileOptions();
			pdfFileOptions.setRenderComments(true); // Default value is false

			// Get Pdf document with comments
			FileContainer container = htmlHandler.getPdfFile(guid, pdfFileOptions);

			System.out.println("Name: " + container.getStream().available());
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:renderDocumentAsPDFWithComments_17.5
	}

	/**
	 * Multiple pages per sheet for Excel files in Html mode
	 * 
	 * @throws Throwable
	 */
	public static void multiplePagesPerSheetForExcelHtmlMode(String fileName) throws Throwable {
		// ExStart:multiplePagesPerSheetForExcel
		// Setup GroupDocs.Viewer config
		ViewerConfig config = Utilities.getConfiguration();
		// Create html handler
		ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);
		String guid = fileName;
		// Set OnePagePerSheet = false to render multiple pages per sheet
		HtmlOptions htmlOptions = new HtmlOptions();
		htmlOptions.getCellsOptions().setOnePagePerSheet(false);
		// Set count rows to render into one page. Default value is 50.
		htmlOptions.getCellsOptions().setCountRowsPerPage(50);

		// Get pages
		List<PageHtml> pages = htmlHandler.getPages(guid, htmlOptions);
		for (PageHtml page : pages) {
			Utilities.saveAsHtml(page.getPageNumber() + "_" + fileName, page.getHtmlContent());
		}
		// System.out.println("Pages count: " + pages.size());
		// ExEnd:multiplePagesPerSheetForExcel
	}

	/**
	 * Multiple pages per sheet for Excel files in Image mode
	 * 
	 * @param fileName
	 * @throws Throwable
	 */
	public static void multiplePagesPerSheetForExcelImageMode(String fileName) throws Throwable {
		// ExStart:multiplePagesPerSheetForExcelImageMode
		// Setup GroupDocs.Viewer config
		ViewerConfig config = Utilities.getConfiguration();
		// Create html handler
		ViewerImageHandler imageHandler = new ViewerImageHandler(config);
		String guid = fileName;
		// Set OnePagePerSheet = false to render multiple pages per sheet. By
		// default OnePagePerSheet = true.
		ImageOptions imageOptions = new ImageOptions();
		imageOptions.getCellsOptions().setOnePagePerSheet(false);

		// Get pages
		List<PageImage> pages = imageHandler.getPages(guid, imageOptions);
		for (PageImage page : pages) {
			Utilities.saveAsImage(page.getPageNumber() + "_" + fileName, "png", page.getStream());
		}
		// ExEnd:multiplePagesPerSheetForExcelImageMode
	}

	/**
	 * Gets HTML representation of the attached documents with an email file
	 * 
	 * @param emailFile
	 *            name of the source email file which contains attachments
	 */
	public static void renderDocumentAsHtmlFromEmailAttachment(String emailFile) {
		// ExStart:GetHtmlRepresentationOfTheAttachment

		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig viewerConfig = Utilities.getConfiguration();

			// Init viewer html handler
			ViewerHtmlHandler handler = new ViewerHtmlHandler(viewerConfig);

			// Clear files from cache
			// handler.clearCache();

			// Setup html conversion options
			HtmlOptions htmlOptions = new HtmlOptions();
			htmlOptions.setEmbedResources(true);

			DocumentInfoContainer info = handler.getDocumentInfo(emailFile);
			// Iterate over the attachments collection
			for (AttachmentBase attachment : info.getAttachments()) {

				List<PageHtml> pages = handler.getPages(attachment, htmlOptions);
				for (PageHtml page : pages) {
					Utilities.saveAsHtml(page.getPageNumber() + "_" + attachment.getName(), page.getHtmlContent());
				}
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:GetHtmlRepresentationOfTheAttachment
	}

	/**
	 * Gets html representation of the N consecutive pages
	 * 
	 * @param fileName
	 *            name of the source document
	 * @param pageNumber
	 *            starting page number
	 * @param countPagesToConvert
	 *            total number of pages to convert
	 */
	public static void renderDocumentAsHtml(String fileName, int pageNumber, int countPagesToConvert) {
		// ExStart:GetHtmlRepresentationOfNConsecutiveDocs
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();
			// Create html handler
			ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);
			String guid = fileName;

			// Options to convert countPagesToConvert consecutive pages starting
			// from pageNumber
			HtmlOptions options = new HtmlOptions();
			options.setPageNumber(pageNumber);
			options.setCountPagesToRender(countPagesToConvert);
			List<PageHtml> pages = htmlHandler.getPages(guid, options);

			for (PageHtml page : pages) {
				Utilities.saveAsHtml(page.getPageNumber() + "_" + fileName, page.getHtmlContent());
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:GetHtmlRepresentationOfNConsecutiveDocs
	}

	/**
	 * render content with RenderLayersSeparately enabled
	 * 
	 * @throws Throwable
	 */
	public static void renderContentWithRenderLayersSeparately(String fileName) throws Throwable {
		// ExStart:renderContentWithRenderLayersSeparately
		// Setup GroupDocs.Viewer config
		ViewerConfig config = Utilities.getConfiguration();
		// Create html handler
		ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);
		String guid = fileName;
		// Set pdf options to render pdf layers into separate html elements
		HtmlOptions options = new HtmlOptions();
		options.getPdfOptions().setRenderLayersSeparately(true); // Default
		// value is
		// false

		// Get pages
		List<PageHtml> pages = htmlHandler.getPages(guid, options);

		for (PageHtml page : pages) {
			System.out.println("Page number: " + page.getPageNumber());
			System.out.println("Html content: " + page.getHtmlContent());
		}
		// ExEnd:renderContentWithRenderLayersSeparately
	}

	/**
	 * Gets html representation of custom page numbers
	 * 
	 * @param fileName
	 *            name of the source document
	 * @param listPagesToConvert
	 *            list of pages to convert
	 */
	public static void renderDocumentAsHtml(String fileName, List<Integer> listPagesToConvert) {
		// ExStart:GetHtmlRepresentationForCustomPageNumberList
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();
			// Create html handler
			ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);
			String guid = fileName;

			// Options to convert 1, 3, 5, 6, 8 page numbers
			HtmlOptions options = new HtmlOptions();
			options.setPageNumbersToRender(listPagesToConvert);

			List<PageHtml> pages = htmlHandler.getPages(guid, options);

			for (PageHtml page : pages) {
				Utilities.saveAsHtml(page.getPageNumber() + "_" + fileName, page.getHtmlContent());
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:GetHtmlRepresentationForCustomPageNumberList
	}

	/**
	 * Gets html representation of the document with rotation
	 * 
	 * @param fileName
	 *            name of the source document
	 * @param rotationAngle
	 *            value of angle
	 */
	public static void renderDocumentAsHtml(String fileName, int rotationAngle) {
		// ExStart:RotatePageToNintyDegreeInHtmlMode
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();
			// Create html handler
			ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);
			String guid = fileName;

			// Set rotation angle 90 for page number 1
			RotatePageOptions rotateOptions = new RotatePageOptions(3, rotationAngle);

			// Perform page rotation
			htmlHandler.rotatePage(guid, rotateOptions);
			// Set html options to include rotate transformation
			HtmlOptions options = new HtmlOptions();
			options.setTransformations(Transformation.Rotate);

			// Get All pages
			List<PageHtml> pages = htmlHandler.getPages(guid, options);

			// Save pages
			for (PageHtml page : pages) {
				Utilities.saveAsHtml(page.getPageNumber() + "_" + fileName, page.getHtmlContent());
			}

		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:RotatePageToNintyDegreeInHtmlMode
	}

	/**
	 * Renders document as html with reorder
	 * 
	 * @param fileName
	 *            name of the source document
	 * @param pageNumber
	 *            page number that needs to be reordered
	 * @param newPosition
	 *            reorder to a new position
	 * @param isTransformation
	 *            isTransformation can be true or false
	 */
	public static void renderDocumentAsHtml(String fileName, int pageNumber, int newPosition,
			boolean isTransformation) {
		// ExStart:ReorderFirstAndSecondPagesInHtmlMode
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Create html handler
			ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);
			String guid = fileName;

			// Perform page reorder
			ReorderPageOptions options = new ReorderPageOptions(pageNumber, newPosition);
			htmlHandler.reorderPage(guid, options);

			// Set image options to include reorder transformations
			HtmlOptions htmlOptions = new HtmlOptions();
			htmlOptions.setTransformations(Transformation.Reorder);

			// Get html representation of all document pages, including rotate
			// transformations
			List<PageHtml> pages = htmlHandler.getPages(guid, htmlOptions);
			int pageNumberCount = 1;
			for (PageHtml page : pages) {
				Utilities.saveAsHtml(pageNumberCount + "_" + fileName, page.getHtmlContent());
				pageNumberCount++;
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:ReorderFirstAndSecondPagesInHtmlMode
	}

	/**
	 * Gets html representation of the document with watermark
	 * 
	 * @param fileName
	 *            name of the source document
	 * @param watermarkText
	 *            text that will show on the image as watermark
	 */
	public static void renderDocumentAsHtml(String fileName, String watermarkText) {
		// ExStart:AddWatermarkToHtmlPageRepresentation
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();
			// Create html handler
			ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);
			String guid = fileName;

			HtmlOptions options = new HtmlOptions();

			// Set watermark properties
			Watermark watermark = new Watermark(watermarkText);
			// Set color for watermark with values Red, Green, Blue and
			// Alpha(Transparency)
			Color watermarkColor = new Color(200, 75, 75, 100);
			watermark.setColor(watermarkColor);
			watermark.setPosition(WatermarkPosition.Diagonal);
			watermark.setWidth(100f);

			// Set watermark tag font-family css property
			watermark.setFontName("\"Comic Sans MS\", cursive, sans-serif");

			options.setWatermark(watermark);

			// Get document pages html representation with watermark
			List<PageHtml> pages = htmlHandler.getPages(guid, options);
			for (PageHtml page : pages) {
				Utilities.saveAsHtml(page.getPageNumber() + "_" + fileName, page.getHtmlContent());
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:AddWatermarkToHtmlPageRepresentation
	}

	/**
	 * Gets html representation of document excluding transformation
	 * 
	 * @param fileName
	 *            name of the source document
	 * @param excludeTransformation
	 *            excludeTransformation true or false
	 */
	public static void renderDocumentAsHtml(String fileName, boolean excludeTransformation) {
		// ExStart:RetrieveAllHtmlPagesExludingTransformation
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();
			// Create html handler
			ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);
			String guid = fileName;

			// Set html options to include rotate transformation
			HtmlOptions noTransformationsOptions = new HtmlOptions();
			noTransformationsOptions.setTransformations(Transformation.None);

			// Get html representation of all document pages, without
			// transformations
			List<PageHtml> pagesWithoutTransformations = htmlHandler.getPages(guid, noTransformationsOptions);

			// Get html representation of all document pages, without
			// transformations
			List<PageHtml> pagesWithoutTransformations2 = htmlHandler.getPages(guid);
			for (PageHtml page : pagesWithoutTransformations) {
				Utilities.saveAsHtml(page.getPageNumber() + "_" + fileName, page.getHtmlContent());
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:RetrieveAllHtmlPagesExludingTransformation
	}

	/**
	 * Gets printable html representation of the document
	 * 
	 * @param fileName
	 *            name of the source document
	 */
	public static void renderDocumentAsHtmlForPrint(String fileName) {
		// ExStart:GetDocumentHtmlForPrint
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();
			// Create image handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);
			String guid = fileName;

			// Get document html for print
			PrintableHtmlOptions options = new PrintableHtmlOptions(guid);
			PrintableHtmlContainer container = imageHandler.getPrintableHtml(guid, options);

			// Save as html
			Utilities.saveAsHtml(fileName, container.getHtmlContent());

		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:GetDocumentHtmlForPrint
	}

	/**
	 * Gets printable html representation of the document with watermark
	 * 
	 * @param fileName
	 *            name of the source document
	 * @param watermarkText
	 *            text that will appear as watermark
	 */
	public static void renderDocumentAsHtmlForPrint(String fileName, String watermarkText) {
		// ExStart:GetDocumentHtmlForPrintWithWatermark
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();
			// Create image handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);
			String guid = fileName;

			// Set watermark properties
			Watermark watermark = new Watermark(watermarkText);
			watermark.setColor(Color.BLUE);
			watermark.setPosition(WatermarkPosition.Diagonal);
			watermark.setWidth(100f);
			// Get document html for print with watermark
			PrintableHtmlOptions options = new PrintableHtmlOptions(guid, watermark);
			PrintableHtmlContainer container = imageHandler.getPrintableHtml(guid, options);

			// Save as html
			Utilities.saveAsHtml(fileName, container.getHtmlContent());
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:GetDocumentHtmlForPrintWithWatermark
	}

	/**
	 * Gets printable html representation of the document with custom CSS
	 * 
	 * @param fileName
	 *            name of the source document
	 */
	public static void renderDocumentAsHtmlForPrintWithCustomCss(String fileName) {
		// ExStart:GetDocumentHtmlForPrintWithCustomCss
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Create image handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);
			String guid = fileName;

			// Get document html for print with custom css
			String css = "a { color: hotpink; }"; // Some style
			PrintableHtmlOptions options = new PrintableHtmlOptions(guid);
			options.setCss(css);
			PrintableHtmlContainer container = imageHandler.getPrintableHtml(guid, options);

			// Save as html
			Utilities.saveAsHtml(fileName, container.getHtmlContent());
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:GetDocumentHtmlForPrintWithCustomCss
	}

	public static void renderDocumentAsHtmlMultipleTransformations(String fileName, int rotationAngle,
			int rotationPageNumber, int reorderPageNumber, int reorderNewPosition) {
		// ExStart:PerformMultipleTransformationsInHtmlMode
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();
			// Create html handler
			ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);
			String guid = fileName;
			RotatePageOptions rotatePage = new RotatePageOptions(rotationPageNumber, rotationAngle);
			// Rotate page
			htmlHandler.rotatePage(guid, rotatePage);

			RotatePageOptions rotatePageOp = new RotatePageOptions(2, 180);
			// Rotate second page 180 degrees
			htmlHandler.rotatePage(guid, rotatePageOp);
			ReorderPageOptions reorderPageOp = new ReorderPageOptions(reorderPageNumber, reorderNewPosition);
			// Reorder pages
			htmlHandler.reorderPage(guid, reorderPageOp);

			// Set options to include rotate and reorder transformations
			HtmlOptions options = new HtmlOptions();
			options.setTransformations(Transformation.Rotate | Transformation.Reorder);

			// Set watermark properties
			Watermark watermark = new Watermark("This is watermark text");
			watermark.setColor(Color.BLUE);
			watermark.setPosition(WatermarkPosition.Diagonal);
			watermark.setWidth(100f);

			options.setWatermark(watermark);

			// Get document pages html representation with multiple
			// transformations
			List<PageHtml> pages = htmlHandler.getPages(guid, options);
			for (PageHtml page : pages) {
				Utilities.saveAsHtml(page.getPageNumber() + "_" + fileName, page.getHtmlContent());
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:PerformMultipleTransformationsInHtmlMode
	}

	/**
	 * Gets resource stream of Html representation of the document
	 * 
	 * @param fileName
	 *            name of the source document
	 */
	public static void getDocumentHtmlResourceStream(String fileName) {
		// ExStart:GetHtmlResourceStream
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();
			// Create html handler
			ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);
			String guid = fileName;

			List<PageHtml> pages = htmlHandler.getPages(guid);

			for (PageHtml page : pages) {
				System.out.println("Page number: " + page.getPageNumber());
				System.out.println("Resources count: " + page.getHtmlResources().size());
				System.out.println("Html content: " + page.getHtmlContent());

				// Html resources descriptions
				for (HtmlResource resource : page.getHtmlResources()) {
					System.out.println(resource.getResourceName() + resource.getResourceType());

					// Get html page resource stream
					InputStream resourceStream = htmlHandler.getResource(guid, resource);
					System.out.println("Stream size: " + resourceStream.available());
				}
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:GetHtmlResourceStream
	}

	/**
	 * Gets Html representation of Word document with tracked changes
	 * 
	 * @param fileName
	 */
	public static void renderWordDocumentAsHtmlWithTrackedChanges(String fileName) {
		// ExStart:renderWordDocumentAsHtmlWithTrackedChanges_17.5
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Create Html handler
			ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);
			String guid = fileName;

			// Set Html options
			HtmlOptions options = new HtmlOptions();
			options.getWordsOptions().setShowTrackedChanges(true); // Default
			// value is
			// false

			// Get pages
			List<PageHtml> pages = htmlHandler.getPages(guid, options);

			for (PageHtml page : pages) {
				System.out.println("Page number: " + page.getPageNumber());
				System.out.println("Html content: " + page.getHtmlContent());
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:renderWordDocumentAsHtmlWithTrackedChanges_17.5
	}

	/**
	 * Gets image representation of Word document with tracked changes
	 * 
	 * @param fileName
	 */
	public static void renderWordDocumentAsImageWithTrackedChanges(String fileName) {
		// ExStart:renderWordDocumentAsImageWithTrackedChanges_17.5
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Create Html handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);
			String guid = fileName;

			// Set Html options
			ImageOptions options = new ImageOptions();
			options.getWordsOptions().setShowTrackedChanges(true); // Default
			// value is
			// false

			// Get pages
			List<PageImage> pages = imageHandler.getPages(guid, options);

			for (PageImage page : pages) {
				System.out.println("Page number: " + page.getPageNumber());
				InputStream content = page.getStream();
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:renderWordDocumentAsImageWithTrackedChanges_17.5
	}

	/**
	 * Gets PDF representation of Word document with tracked changes
	 * 
	 * @param fileName
	 */
	public static void renderWordDocumentAsPDFWithTrackedChanges(String fileName) {
		// ExStart:renderWordDocumentAsPDFWithTrackedChanges_17.5
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Create Html handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);
			String guid = fileName;

			// Set pdf options to get pdf file with tracked changes
			PdfFileOptions pdfFileOptions = new PdfFileOptions();
			pdfFileOptions.getWordsOptions().setShowTrackedChanges(true); // Default
			// value
			// is
			// false

			// Get pdf document with tracked changes
			FileContainer fileContainer = imageHandler.getPdfFile(guid, pdfFileOptions);
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:renderWordDocumentAsPDFWithTrackedChanges_17.5
	}

	/**
	 * Note: In order to run this function, please import ASPOSE PDF JAR. Gets
	 * Html of a PDF document with layered content
	 * 
	 * @param fileName
	 *            name of the source document
	 */
	/*
	 * public static void renderPdfAsHtmlWithLayers(String fileName) { //
	 * ExStart:GetHtmlRepresentationOfPdfWithLayers try { //Get Guid of the pdf
	 * dcument String guid = fileName;
	 * 
	 * //Set Html Options HtmlSaveOptions saveOptions = new HtmlSaveOptions();
	 * 
	 * //Setting Marked Content to Layers while Converting
	 * saveOptions.setConvertMarkedContentToLayers(true);
	 * 
	 * //Saving document as HTML Document document = new
	 * Document("Data/Storage/" + guid); document.save("Data/Output/Html/output"
	 * + guid + ".html", saveOptions); } catch (Exception exp) {
	 * System.out.println("Exception: " + exp.getMessage());
	 * exp.printStackTrace(); } // ExEnd:GetGetHtmlRepresentationOfPdfWithLayers
	 * }
	 */

	/**
	 * Note: In order to run this function, please import ASPOSE Diagram JAR.
	 * Gets Image of a Diagram document
	 * 
	 * @param fileName
	 *            name of the source diagram document
	 *
	 * @param DefaultFont
	 *            name of the Font to use as default font in the diagram
	 */
	/*
	 * public static void renderDiagramAsImageWithDefaultFont(String fileName,
	 * String DefaultFont) { //
	 * ExStart:GetImageRepresentationOfDiagramWithDefaultFont try { //Get Guid
	 * of the Diagram dcument String guid = fileName;
	 * 
	 * //Set Html Options ImageSaveOptions saveOptions = new
	 * ImageSaveOptions(0);
	 * 
	 * //Setting Default Font saveOptions.setDefaultFont(DefaultFont);
	 * 
	 * //Saving document as Image Diagram diagram = new Diagram("Data/Storage/"
	 * + guid); diagram.save("Data/Output/Html/output" + guid + ".png",
	 * saveOptions); } catch (Exception exp) { System.out.println("Exception: "
	 * + exp.getMessage()); exp.printStackTrace(); } //
	 * ExEnd:GetImageRepresentationOfDiagramWithDefaultFont }
	 */

	/**
	 * Note: In order to run this function, please import ASPOSE Diagram JAR.
	 * Gets Html of a Diagram document
	 * 
	 * @param fileName
	 *            name of the source diagram document
	 *
	 * @param DefaultFont
	 *            name of the Font to use as default font in the diagram
	 */
	/*
	 * public static void renderDiagramAsHtmlWithDefaultFont(String fileName,
	 * String DefaultFont) { //
	 * ExStart:GetHtmlRepresentationOfDiagramWithDefaultFont try { //Get Guid of
	 * the Diagram dcument String guid = fileName;
	 * 
	 * //Set Html Save Options HTMLSaveOptions saveOptions = new
	 * HTMLSaveOptions();
	 * 
	 * //Enabling Default Font saveOptions.setDefaultFont(DefaultFont);
	 * 
	 * //Saving document as HTML Diagram diagram = new Diagram("Data/Storage/" +
	 * guid); diagram.save("Data/Output/Html/output" + guid + ".html",
	 * saveOptions); } catch (Exception exp) { System.out.println("Exception: "
	 * + exp.getMessage()); exp.printStackTrace(); } //
	 * ExEnd:GetHtmlRepresentationOfDiagramWithDefaultFont }
	 */

	/**
	 * Note: In order to run this function, please import ASPOSE Diagram JAR.
	 * Gets PDF of a Diagram document
	 * 
	 * @param fileName
	 *            name of the source diagram document
	 *
	 * @param DefaultFont
	 *            name of the Font to use as default font in the diagram
	 */
	/*
	 * public static void renderDiagramAsPdfWithDefaultFont(String fileName,
	 * String DefaultFont) { //
	 * ExStart:GetPdfRepresentationOfDiagramWithDefaultFont try { //Get Guid of
	 * the pdf dcument String guid = fileName;
	 * 
	 * //Set Pdf Save Options PdfSaveOptions saveOptions = new PdfSaveOptions();
	 * 
	 * //Setting Default Font saveOptions.setDefaultFont(DefaultFont);
	 * 
	 * //Saving document as Pdf Diagram diagram = new Diagram("Data/Storage/" +
	 * guid); diagram.save("Data/Output/Html/output" + guid + ".pdf",
	 * saveOptions); } catch (Exception exp) { System.out.println("Exception: "
	 * + exp.getMessage()); exp.printStackTrace(); } //
	 * ExEnd:GetPdfRepresentationOfDiagramWithDefaultFont }
	 */

	/**
	 * Saves original file
	 * 
	 * @param fileName
	 *            name of the source document
	 */
	public static void getOriginalFile(String fileName) {
		// ExStart:GetOriginalFile
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();
			// Create image handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);

			String guid = fileName;

			// Get file in container
			FileContainer container = imageHandler.getFile(guid);

			// Save file
			Utilities.saveAsFile(fileName, container.getStream());

		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:GetOriginalFile
	}

	/**
	 * Gets original file as pdf without any transformation
	 * 
	 * @param fileName
	 *            name of the source document
	 */
	public static void getOriginalFileAsPdf(String fileName) {
		// ExStart:GetOriginalPdfDocWithoutTransformation
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Create image handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);

			PdfFileOptions options = new PdfFileOptions();

			options.setTransformations(Transformation.AddPrintAction);

			// Get file as pdf
			FileContainer container = imageHandler.getPdfFile(fileName, options);

			// Save file
			Utilities.saveAsFile(fileName, container.getStream(), ".pdf");

		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:GetOriginalPdfDocWithoutTransformation
	}

	/**
	 * Renders document as PDF using custom JPEG quality settings
	 * 
	 * @param fileName
	 *            name of the source document
	 */
	public static void renderDocumentAsPdfWithJPEGQuality(String fileName) {
		// ExStart:renderDocumentAsPdfWithJPEGQuality_17.5
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Create image handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);

			// Set pdf options JpegQuality in a range between 1 and 100
			PdfFileOptions pdfFileOptions = new PdfFileOptions();
			pdfFileOptions.setJpegQuality(5);

			String guid = fileName;

			// Get file as pdf
			FileContainer container = imageHandler.getPdfFile(guid, pdfFileOptions);

			System.out.println("Name: " + container.getStream().available());

		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:renderDocumentAsPdfWithJPEGQuality_17.5
	}

	/**
	 * Gets PDF file without any annotations
	 * 
	 * @param fileName
	 */
	public static void getPdfFileWithoutAnnotations(String fileName) {
		// ExStart:getPdfFileWithoutAnnotations_17.5
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			ViewerImageHandler imageHandler = new ViewerImageHandler(config);
			// Set pdf options to get original file without annotations
			PdfFileOptions pdfFileOptions = new PdfFileOptions();
			//pdfFileOptions.getPdfOptions().setDeleteAnnotations(true); // Default
			// value
			// is
			// false

			String guid = fileName;

			// Get original Pdf document without annotations
			FileContainer fileContainer = imageHandler.getPdfFile(guid, pdfFileOptions);
			// Access resultant Pdf document using fileContainer.Stream property

			// Save file
			Utilities.saveAsFile(fileName, fileContainer.getStream());

		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:getPdfFileWithoutAnnotations_17.5
	}

	/**
	 * Gets original file as pdf along with watermark
	 * 
	 * @param fileName
	 *            name of the source document
	 * @param watermarkText
	 *            text that will appear on the document as watermark
	 */
	public static void getOriginalFileAsPdfWithWatermark(String fileName, String watermarkText) {
		// ExStart:GetOriginalPdfDocWithWatermark
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();
			// Create image handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);

			// Set watermark properties
			Watermark watermark = new Watermark(watermarkText);
			watermark.setColor(Color.blue);
			watermark.setPosition(WatermarkPosition.Diagonal);
			watermark.setWidth(100f);

			// Set watermark font name which contains Japanese characters
			watermark.setFontName("MS Gothic");

			PdfFileOptions options = new PdfFileOptions();

			options.setTransformations(Transformation.AddPrintAction);

			options.setWatermark(watermark);

			// Get file as pdf with watermaks
			FileContainer container = imageHandler.getPdfFile(fileName, options);

			// Save file
			Utilities.saveAsFile(fileName, container.getStream(), ".pdf");
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:GetOriginalPdfDocWithWatermark
	}

	/**
	 * Gets original file as pdf with print action
	 * 
	 * @param fileName
	 *            name of the source document
	 */
	public static void getOriginalFileAsPdfWithPrintAction(String fileName) {
		// ExStart:GetOriginalPdfDocWithPrintAction
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();
			// Create image handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);

			// Set add print action property
			PdfFileOptions options = new PdfFileOptions();
			options.setTransformations(Transformation.AddPrintAction);

			// Get file as pdf with print action
			FileContainer container = imageHandler.getPdfFile(fileName, options);
			Utilities.saveAsFile(fileName, container.getStream(), ".pdf");
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:GetOriginalPdfDocWithPrintAction
	}

	/**
	 * Gets original file as pdf with transformations
	 * 
	 * @param fileName
	 *            name of the source document
	 */
	public static void getOriginalFileAsPdfWithTransformation(String fileName) {
		// ExStart:GetOriginalPdfDocWithTransformation
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();
			// Create image handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);

			// Perform page rotation
			RotatePageOptions rotatePageOptions = new RotatePageOptions(1, 90);
			imageHandler.rotatePage(fileName, rotatePageOptions);

			// Reorder pages, move 1 page to the 2 position, it is assumed that
			// "word.doc" document has at least two pages
			ReorderPageOptions reorderPageOptions = new ReorderPageOptions(1, 2);
			imageHandler.reorderPage(fileName, reorderPageOptions);

			// Set apply rotate and reorder transformations
			PdfFileOptions options = new PdfFileOptions();
			options.setTransformations(Transformation.Rotate | Transformation.Reorder | Transformation.AddPrintAction);

			// Get file as pdf with transformations
			FileContainer container = imageHandler.getPdfFile(fileName, options);

			// Save file
			Utilities.saveAsFile(fileName, container.getStream(), ".pdf");
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:GetOriginalPdfDocWithTransformation
	}
	/**
	 * Check if source PDF document has restriction on printing
	 * 
	 *
	 */
	public static void checkPrintingRestriction(String fileName) {
		try {
			//ExStart:CheckPDFPrintingRestriction_19.3
			
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();
			
			// Create image handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);
			
			//Document Name
			String guid = fileName;
			
			// Retrieve document information
			PdfDocumentInfoContainer documentInfo = (PdfDocumentInfoContainer)imageHandler.getDocumentInfo(guid);
			
			// check if printing restrictions are existed or not
			boolean printingAllowed = documentInfo.getPrintingAllowed();
			
			if (printingAllowed)
	        {
				System.out.println("The PDF is ready for printing.");
	        }
	        else
	        {
	        	System.out.println("Restricted file: The printing is not allowed");
	        }
			//ExEnd:CheckPDFPrintingRestriction_19.3
			
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		
	}

	/**
	 * Loads file tree list for the storage path
	 * 
	 */
	public static void loadFileTreeList() {
		// ExStart:LoadFileTreeListForStoragePath
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();
			// Create image handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);

			// Load file tree list for ViewerConfig.StoragePath
			FileListContainer container = imageHandler.getFileList();

			for (FileDescription node : container.getFiles()) {
				if (node.isDirectory()) {
					System.out.println("Guid: " + node.getGuid() + " | Name: " + node.getName()
							+ " | LastModificationDate: " + node.getLastModificationDate());
				} else {
					System.out.println("Guid: " + node.getGuid() + " | Name: " + node.getName() + " | Document type: "
							+ node.getDocumentType() + " | File type: " + node.getFileType() + " | Extension: "
							+ node.getExtension() + " | Size: " + node.getSize() + " | LastModificationDate: "
							+ node.getLastModificationDate());
				}
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:LoadFileTreeListForStoragePath
	}

	/**
	 * Loads file tree list for the custom storage path
	 * 
	 * @param customPath
	 *            customPath of the storage
	 */
	public static void loadFileTreeList(String customPath) {
		// ExStart:LoadFileTreeListForCustomPath
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();
			// Create image handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);

			// Load file tree list for custom path
			FileListOptions options = new FileListOptions(customPath);

			FileListContainer container = imageHandler.getFileList(options);

			for (FileDescription node : container.getFiles()) {
				if (node.isDirectory()) {
					System.out.println("Guid: " + node.getGuid() + " | Name: " + node.getName()
							+ " | LastModificationDate: " + node.getLastModificationDate());
				} else {
					System.out.println("Guid: " + node.getGuid() + " | Name: " + node.getName() + " | Document type: "
							+ node.getDocumentType() + " | File type: " + node.getFileType() + " | Extension: "
							+ node.getExtension() + " | Size: " + node.getSize() + " | LastModificationDate: "
							+ node.getLastModificationDate());
				}
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:LoadFileTreeListForCustomPath
	}

	/**
	 * Loads ordered file tree list for the custom storage path with order
	 * 
	 * @param customPath
	 *            customPath of the storage
	 */
	public static void loadFileTreeListWithOrder(String customPath) {
		// ExStart:loadFileTreeListForCustomPathWithOrder
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();
			// Create image handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);

			// Load file list sorted by Name and ordered Ascending for custom
			// path
			//19.1 change
			FileListOptions options = new FileListOptions(customPath, FileListOptions.FileListSortBy.NAME,
					FileListOptions.FileListOrderBy.ASCENDING);

			// Load file list
			FileListContainer container = imageHandler.getFileList(options);

			for (FileDescription node : container.getFiles()) {
				if (node.isDirectory()) {
					System.out.println("Guid: " + node.getGuid() + " | Name: " + node.getName()
							+ " | LastModificationDate: " + node.getLastModificationDate());
				} else {
					System.out.println("Guid: " + node.getGuid() + " | Name: " + node.getName() + " | Document type: "
							+ node.getDocumentType() + " | File type: " + node.getFileType() + " | Extension: "
							+ node.getExtension() + " | Size: " + node.getSize() + " | LastModificationDate: "
							+ node.getLastModificationDate());
				}
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:LoadFileTreeListForCustomPathWithOrder
	}

	/**
	 * Renders document with CustomInputDataHandler
	 * 
	 * @param fileName
	 *            name of the document
	 */
	public static void renderDocumentWithCustomDataHandler(String fileName) {
		// ExStart: RenderDocumentWithCustomDataHandler
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// File guid
			String guid = fileName;

			// Use custom IInputDataHandler implementation
			IInputDataHandler inputDataHandler = new AzureInputDataHandler("<Account_Name>", "<Account_Key>",
					"<Container_Name>");

			// Get file HTML representation
			ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config, inputDataHandler);

			List<PageHtml> pages = htmlHandler.getPages(guid);
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd: RenderDocumentWithCustomDataHandler
	}

	/**
	 * Renders excel document as image with gridlines
	 * 
	 * @param fileName
	 *            name of the document
	 */
	public static void renderExcelAsImageWithGridLines(String fileName) {
		// ExStart:renderExcelDocumentAsImageWithGridLines
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// File guid
			String guid = fileName;

			// Create image handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);

			// Set image options to show grid lines
			ImageOptions options = new ImageOptions();
			options.getCellsOptions().setShowGridLines(true);

			List<PageImage> pages = imageHandler.getPages(guid, options);

			for (PageImage page : pages) {
				System.out.printf("Page number: " + page.getPageNumber());

				// Page image stream
				Utilities.saveAsImage(page.getPageNumber() + "_" + fileName, "png", page.getStream());
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:renderExcelDocumentAsImageWithGridLines
	}

	/**
	 * Renders excel document as Html with HyperLink Prefix
	 * 
	 * @param fileName
	 *            name of the document
	 * @param hyperLinkPrefix
	 *            HyperLink Prefix of the link in the document
	 */
	public static void renderExcelAsHtmlWithHyperLinkPrefix(String fileName, String hyperLinkPrefix) {
		// ExStart:renderExcelDocumentAsHtmlWithHyperLinkPrefix
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// File guid
			String guid = fileName;

			// Create html handler
			ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);

			// Set html options to show hyper link prefix
			HtmlOptions options = new HtmlOptions();
			options.getCellsOptions().setInternalHyperlinkPrefix(hyperLinkPrefix);
			List<PageHtml> pages = htmlHandler.getPages(guid, options);

			for (PageHtml page : pages) {
				System.out.println("Page number: " + page.getPageNumber());
				System.out.println("Html content: " + page.getHtmlContent());
				Utilities.saveAsHtml(page.getPageNumber() + "_" + fileName, page.getHtmlContent());
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:renderExcelDocumentAsHtmlWithHyperLinkPrefix
	}

	/**
	 * Renders Excel document as html with multiple pages per sheet
	 * 
	 * @param fileName
	 *            name of the document
	 */
	public static void renderExcelAsImagesWithMultiplePagesPerSheet(String fileGuid) {
		// ExStart:renderExcelAsImageWithMultiplePagesPerSheet
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Create image or html handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);

			// Set pdf file one page per sheet option to false, default value of
			// this option is true
			ImageOptions imageOptions = new ImageOptions();
			imageOptions.getCellsOptions().setOnePagePerSheet(false);

			// Get pages
			List<PageImage> pageImages = imageHandler.getPages(fileGuid, imageOptions);
			// Save pages
			for (PageImage page : pageImages) {
				Utilities.saveAsImage(page.getPageNumber() + "_" + fileGuid, "png", page.getStream());
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:renderExcelAsImageWithMultiplePagesPerSheet
	}

	/**
	 * Gets all supported formats
	 * 
	 */
	public static void getAllSupportedFormats() {
		// ExStart: getAllSupportedFormats
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Create image or html handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);

			// Get supported document formats
			DocumentFormatsContainer documentFormatsContainer = imageHandler.getSupportedDocumentFormats();
			Map<String, String> supportedDocumentFormats = documentFormatsContainer.getSupportedDocumentFormats();

			for (Map.Entry<String, String> supportedDocumentFormat : supportedDocumentFormats.entrySet()) {
				System.out.println(String.format("Extension: '%s'; Document format: '%s'",
						supportedDocumentFormat.getKey(), supportedDocumentFormat.getValue()));
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd: getAllSupportedFormats
	}

	/**
	 * Renders Excel as HTML with text overflow settings
	 * 
	 * @param fileName
	 */
	public static void renderExcelAsHTMLWithTextOverFlowSettings(String fileName) {
		// ExStart: renderExcelAsHTMLWithTextOverFlowSettings_17.5
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Create html handler
			ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);
			String guid = fileName;

			// Set Cells options to hide overflowing text
			HtmlOptions options = new HtmlOptions();
			options.getCellsOptions().setTextOverflowMode(TextOverflowMode.HideText);

			// Get pages
			List<PageHtml> pages = htmlHandler.getPages(guid, options);

			for (PageHtml page : pages) {
				System.out.println("Page number: " + page.getPageNumber());
				final String htmlContent = page.getHtmlContent();
				System.out.println("Html content: " + htmlContent);
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd: renderExcelAsHTMLWithTextOverFlowSettings_17.5
	}

	/**
	 * Renders Excel as image with grid lines
	 * 
	 * @param fileName
	 */
	public static void renderExcelAsImageWithGridlines(String fileName) {
		// ExStart: renderExcelAsImageWithGridlines
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Create image handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);
			String guid = fileName;

			// Set image options to show grid lines
			ImageOptions options = new ImageOptions();
			options.getCellsOptions().setShowGridLines(true);

			List<PageImage> pages = imageHandler.getPages(guid, options);

			for (PageImage page : pages) {
				System.out.printf("Page number: " + page.getPageNumber());

				// Page image stream
				final InputStream imageContent = page.getStream();
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd: renderExcelAsImageWithGridlines
	}

	/**
	 * Renders Visio files as image with hidden pages
	 * 
	 * @param fileName
	 *            name of the document
	 */
	public static void renderVisioAsImagesWithHiddenPages(String fileName) {
		// ExStart:renderVisioAsImageWithHiddenPages
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Create image handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);
			String guid = fileName;

			// Set image options to show hidden pages
			ImageOptions options = new ImageOptions();
			// Removed in 19.1
			//options.getDiagramOptions().setShowHiddenPages(true);

			DocumentInfoContainer container = imageHandler.getDocumentInfo(guid);

			for (PageData page : container.getPages())
				System.out.println("Page number: " + page.getNumber() + " Page Name: " + page.getName() + " IsVisible: "
						+ page.isVisible());

			List<PageImage> pages = imageHandler.getPages(guid, options);

			for (PageImage page : pages) {
				System.out.println("Page number: " + page.getPageNumber());

				// Page image stream
				InputStream imageContent = page.getStream();

				// Save Images
				Utilities.saveAsImage(page.getPageNumber() + "_" + fileName, "png", page.getStream());
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:renderVisioAsImageWithHiddenPages
	}

	/**
	 * Renders Visio files as html with hidden pages
	 * 
	 * @param fileName
	 *            name of the document
	 */
	public static void renderVisioAsHtmlWithHiddenPages(String fileName) {
		// ExStart:renderVisioAsHtmlWithHiddenPages
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Create html handler
			ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);
			String guid = fileName;

			// Set html options to show grid lines
			HtmlOptions options = new HtmlOptions();
			// 19.1 change
			options.setEmbedResources(true);
			//Removed in 19.1
			//options.getDiagramOptions().setShowHiddenPages(true);

			DocumentInfoContainer container = htmlHandler.getDocumentInfo(guid);

			for (PageData page : container.getPages())
				System.out.println("Page number: " + page.getNumber() + " Page Name: " + page.getName() + " IsVisible: "
						+ page.isVisible());

			List<PageHtml> pages = htmlHandler.getPages(guid, options);
			for (PageHtml page : pages) {
				System.out.println("Page number: " + page.getPageNumber());

				// Page Html stream
				// InputStream HtmlContent = page.getStream();
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:renderVisioAsHtmlWithHiddenPages
	}

	/**
	 * Renders Excel as image with hidden sheets
	 * 
	 * @param fileName
	 *            name of the document
	 */
	public static void renderExcelAsImageWithHiddenSheets(String fileName) {
		// ExStart: renderExcelAsImageWithHiddenSheets2
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Create image or html handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);
			String guid = fileName;

			// Set image options to show grid lines
			ImageOptions options = new ImageOptions();
			//Removed in 19.1
			//options.getCellsOptions().setShowHiddenSheets(true);

			DocumentInfoContainer container = imageHandler.getDocumentInfo(guid, new DocumentInfoOptions());

			for (PageData page : container.getPages())
				System.out.println(String.format("Page number: %d, Page Name: %s, IsVisible: %s", page.getNumber(),
						page.getName(), page.isVisible()));

			List<PageImage> pages = imageHandler.getPages(guid, options);

			for (PageImage page : pages) {
				System.out.println("Page number: " + page.getPageNumber());

				// Page image stream
				final InputStream imageContent = page.getStream();
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd: renderExcelAsImageWithHiddenSheets2
	}

	/**
	 * Renders CAD document as image with custom document size
	 * 
	 * @param fileName
	 */
	public static void renderCADDocumentWithCustomDocumentSize(String fileName) {
		// ExStart: renderCADDocumentWithCustomDocumentSize_17.5
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Create image or html handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);
			String guid = fileName;

			// Set Cad options to render content with a specified size
			ImageOptions options = new ImageOptions();
			options.getCadOptions().setHeight(750);
			options.getCadOptions().setWidth(450);

			// Get pages
			List<PageImage> pages = imageHandler.getPages(guid, options);

			for (PageImage page : pages) {
				System.out.println("Page number: " + page.getPageNumber());
				InputStream imageContent = page.getStream();
			}

		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd: renderCADDocumentWithCustomDocumentSize_17.5
	}

	/**
	 * Renders Model and all non empty layouts from CAD document
	 * 
	 * @param fileName
	 */
	public static void renderLayoutsOfCADDocument(String fileName) {
		// ExStart:renderLayoutsOfCADDocument_17.5
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Create image or html handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);
			String guid = fileName;

			// Set CAD options to render Model and all non empty Layouts
			ImageOptions options = new ImageOptions();
			options.getCadOptions().setRenderLayouts(true);

			// Get pages
			List<PageImage> pages = imageHandler.getPages(guid, options);

			for (PageImage page : pages) {
				System.out.println("Page number: " + page.getPageNumber());
				InputStream imageContent = page.getStream();
			}

		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:renderLayoutsOfCADDocument_17.5
	}

	/**
	 * Renders specific layout from CAD document
	 * 
	 * @param fileName
	 */
	public static void renderSpecificLayoutOfCADDocument(String fileName) {
		// ExStart:renderSpecificLayoutOfCADDocument_17.5
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Create image or html handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);
			String guid = fileName;

			// Set CAD options to render Model and all non empty Layouts
			ImageOptions options = new ImageOptions();
			options.getCadOptions().setLayoutName("MyFirstLayout");

			// Get pages
			List<PageImage> pages = imageHandler.getPages(guid, options);

			for (PageImage page : pages) {
				System.out.println("Page number: " + page.getPageNumber());
				InputStream imageContent = page.getStream();
			}

		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:renderSpecificLayoutOfCADDocument_17.5
	}

	/**
	 * Gets list of layouts in a CAD document
	 * 
	 * @param fileName
	 */
	public static void getListOfLayoutInCADDocument(String fileName) {
		// ExStart:getListOfLayoutInCADDocument_17.5
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Create image or html handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);
			String guid = fileName;

			// Set CAD options to get the full list of layouts
			DocumentInfoOptions documentInfoOptions = new DocumentInfoOptions();
			documentInfoOptions.getCadOptions().setRenderLayouts(true);

			// Get DocumentInfoContainer and iterate through pages
			DocumentInfoContainer documentInfoContainer = imageHandler.getDocumentInfo(guid, documentInfoOptions);

			System.out.println("Name: " + documentInfoContainer.getName());
			System.out.println("Name: " + documentInfoContainer.getPages());

		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd:getListOfLayoutInCADDocument_17.5
	}

	/**
	 * Renders PDF document without annotations
	 * 
	 * @param fileName
	 */
	public static void renderPDFDocumentWithoutAnnotations(String fileName) {
		// ExStart: renderPDFDocumentWithoutAnnotations_17.5
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Create image or html handler
			ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);
			String guid = fileName;

			// Set pdf options to render content without annotations
			HtmlOptions options = new HtmlOptions();
			//Removed in 19.1
			//options.getPdfOptions().setDeleteAnnotations(true); // Default value
			// is false

			// Get pages
			List<PageHtml> pages = htmlHandler.getPages(guid, options);

			for (PageHtml page : pages) {
				System.out.println("Page number: " + page.getPageNumber());
				System.out.println("Html content: " + page.getHtmlContent());
			}

		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd: renderPDFDocumentWithoutAnnotations_17.5
	}

	/**
	 * Sets locales path in Viewer Configuration
	 * 
	 */
	public static void setLocalesPath() {
		// ExStart: setLocalesPath
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();
			config.setLocalesPath("D://locales//");

			// Create html handler
			CultureInfo cultureInfo = new CultureInfo("fr-FR");
			ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config, cultureInfo);
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd: setLocalesPath
	}

	/**
	 * Sets encoding of Word, Cells, Email documents
	 * 
	 */
	public static void setDocumentEncoding() {
		// ExStart: setDocumentEncoding
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Initialize viewer handler
			ViewerImageHandler viewerImageHandler = new ViewerImageHandler(config);

			// Set image options
			ImageOptions imageOptions = new ImageOptions();

			// Set encoding
			Charset encoding = Charset.forName("shift-jis");

			// Get words document pages with encoding
			String wordsDocumentGuid = "document.txt";
			List<PageImage> wordsDocumentPages = viewerImageHandler.getPages(wordsDocumentGuid, imageOptions);

			// Get cells document pages with encoding
			String cellsDocumentGuid = "document.csv";
			List<PageImage> cellsDocumentPages = viewerImageHandler.getPages(cellsDocumentGuid, imageOptions);

			// Get email document pages with encoding
			String emailDocumentGuid = "document.msg";
			List<PageImage> emailDocumentPages = viewerImageHandler.getPages(emailDocumentGuid, imageOptions);

			// Get words document info with encoding
			DocumentInfoOptions wordsDocumentInfoOptions = new DocumentInfoOptions();
			wordsDocumentInfoOptions.getWordsOptions().setEncoding(encoding);
			DocumentInfoContainer wordsDocumentInfoContainer = viewerImageHandler.getDocumentInfo(wordsDocumentGuid,
					wordsDocumentInfoOptions);

			// Get cells document info with encoding
			DocumentInfoOptions cellsDocumentInfoOptions = new DocumentInfoOptions();
			cellsDocumentInfoOptions.getCellsOptions().setEncoding(encoding);
			DocumentInfoContainer cellsDocumentInfoContainer = viewerImageHandler.getDocumentInfo(cellsDocumentGuid,
					cellsDocumentInfoOptions);

			// Get email document info with encoding
			DocumentInfoOptions emailDocumentInfoOptions = new DocumentInfoOptions();
			emailDocumentInfoOptions.getEmailOptions().setEncoding(encoding);
			DocumentInfoContainer emailDocumentInfoContainer = viewerImageHandler.getDocumentInfo(emailDocumentGuid,
					emailDocumentInfoOptions);
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd: setDocumentEncoding
	}

	/**
	 * Sets custom fonts directory
	 * 
	 */
	public static void setCustomFontDirectory() {
		// ExStart: setCustomFontDirectory
		try {
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Init viewer handler with config
			ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);

		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		// ExEnd: setCustomFontDirectory
	}

	/**
	 * Renders Presentation document into Html along with the slide notes
	 * 
	 */

	public static void renderPresentationDocumentWithNotes(String DocumentName)
	{
		//ExStart:RenderPresentationDocumentWithNotes_19.1

		try {
			// Get Configurations
			ViewerConfig config = Utilities.getConfiguration();
			// Create html handler
			ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);

			// Set guid
			String guid = DocumentName;

			// Instantiate HtmlOptions object
			HtmlOptions options = new HtmlOptions();
			//(Starting from 19.1)
			options.getSlidesOptions().setRenderNotes(true); // Default value is false

			// Get document pages in Html form
			List<PageHtml> pages= htmlHandler.getPages(guid, options);


			for (PageHtml page : pages)
			{
				//Save each page at disk
				Utilities.saveAsHtml(page.getPageNumber() + "_" + DocumentName, page.getHtmlContent());
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		//ExEnd:RenderPresentationDocumentWithNotes_19.1
	}

	/**
	 * Renders MS Project document into Html with ProjectOptions setting
	 * 
	 */
	public static void renderProjectDocumentAsHtmlWithProjectOptions(String DocumentName)
	{
		//ExStart:RenderProjectDocumentAsHtmlWithProjectOptions_19.1
		try{
			//Get Configurations
			ViewerConfig config = Utilities.getConfiguration();

			// Create html handler
			ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);

			// Guid implies that unique document name 
			String guid = DocumentName;

			//Instantiate the HtmlOptions object
			HtmlOptions options = new HtmlOptions();

			options.getProjectOptions().setPageSize(PageSize.A2);
			options.getProjectOptions().setTimeUnit(TimeUnit.Days);

			//Get document pages in html form
			List<PageHtml> pages = htmlHandler.getPages(guid, options);

			for (PageHtml page : pages)
			{
				//Save each page at disk
				Utilities.saveAsHtml(page.getPageNumber() + "_" + DocumentName, page.getHtmlContent());
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		//ExEnd:RenderProjectDocumentAsHtmlWithProjectOptions_19.1
	}
	/**
	 * Renders MS Project document into Images with ProjectOptions setting
	 * 
	 */
	public static void renderProjectDocumentAsImageWithProjectOptions(String DocumentName)
	{
		//ExStart:RenderProjectDocumentAsImageWithProjectOptions_19.1
		try{
			//Get Configurations
			ViewerConfig config = Utilities.getConfiguration();

			// Create image handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);

			// Guid implies that unique document name 
			String guid = DocumentName;

			//Instantiate the ImageOptions object
			ImageOptions options = new ImageOptions();

			options.getProjectOptions().setPageSize(PageSize.A2);
			options.getProjectOptions().setTimeUnit(TimeUnit.Days);

			//Get document pages in html form
			List<PageImage> pages = imageHandler.getPages(guid, options);

			for (PageImage page : pages)
			{
				//Save each page at disk
				Utilities.saveAsImage(page.getPageNumber() + "_" + DocumentName, "png", page.getStream());
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		//ExEnd:RenderProjectDocumentAsImageWithProjectOptions_19.1
	}
	/**
	 * Renders MS Project document as PDF with ProjectOptions setting
	 * 
	 */
	public static void renderProjectDocumentAsPDFWithProjectOptions(String DocumentName)
	{
		//ExStart:RenderProjectDocumentAsPDFWithProjectOptions_19.1

		try{
			//Get Configurations
			ViewerConfig config = Utilities.getConfiguration();

			// Create html handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);

			// Guid implies that unique document name 
			String guid = DocumentName;

			// Set Project options to render content with a specified size and time unit.
			PdfFileOptions options = new PdfFileOptions();
			options.getProjectOptions().setPageSize(PageSize.A2);
			options.getProjectOptions().setTimeUnit(TimeUnit.Days);

			// Get PDF file 
			FileContainer fileContainer = imageHandler.getPdfFile(guid, options);

			// Set file name
			String filename = Path.getFileNameWithoutExtension(DocumentName) + ".pdf";

			//Save file at disk
			Utilities.saveAsFile(filename, fileContainer.getStream());
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		//ExEnd:RenderProjectDocumentAsPDFWithProjectOptions_19.1
	}
	/**
	 * Renders part of MS Project document with time interval as PDF
	 * 
	 */

	@SuppressWarnings("deprecation")
	public static void renderMSProjectDocumentAsPDFWithSpecifiedTimeInterval(String DocumentName)
	{
		//ExStart:RenderMSProjectDocumentAsPDFWithSpecifiedTimeInterval_19.1
		try
		{
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			String guid = DocumentName;

			// Create Image handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);

			// Set Project options to render tasks from year 2018 only.
			PdfFileOptions options = new PdfFileOptions();
			options.getProjectOptions().setStartDate(new Date(2018, 01, 01));
			options.getProjectOptions().setEndDate(new Date(2018, 12, 31));

			// Get PDF file 
			FileContainer fileContainer = imageHandler.getPdfFile(guid, options);

			// Set file name
			String filename = Path.getFileNameWithoutExtension(DocumentName) + ".pdf";

			//Save file at disk
			Utilities.saveAsFile(filename, fileContainer.getStream());
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		//ExEnd:RenderMSProjectDocumentAsPDFWithSpecifiedTimeInterval_19.1
	}
	/**
	 * Renders part of MS Project document with time interval
	 * 
	 */

	@SuppressWarnings("deprecation")
	public static void renderMSProjectDocumentWithSpecifiedTimeInterval(String DocumentName)
	{
		//ExStart:RenderMSProjectDocumentWithSpecifiedTimeInterval_19.1
		try
		{
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			String guid = DocumentName;

			// Create html handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);

			// Set Project options to render tasks from year 2018 only
			ImageOptions options = new ImageOptions();
			options.getProjectOptions().setStartDate(new Date(2018, 01, 01));
			options.getProjectOptions().setEndDate(new Date(2018, 12, 31));

			List<PageImage> pages = imageHandler.getPages(guid, options);

			for (PageImage page : pages)
			{
				// Save each image at disk
				Utilities.saveAsImage(page.getPageNumber() + "_" + DocumentName, "png", page.getStream());
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		//ExEnd:RenderMSProjectDocumentWithSpecifiedTimeInterval_19.1
	}
	/**
	 * Obtains start and end dates from MS Project document
	 * 
	 */

	public static void obtainStartAndEndDateFromMSProjectDocument(String DocumentName)
	{
		//ExStart:ObtainStartAndEndDateFromMSProjectDocument_19.1
		try
		{
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Set guid
			String guid = DocumentName;

			// Create html handler
			ViewerImageHandler imageHandler = new ViewerImageHandler(config);

			ProjectDocumentInfoContainer documentInfo = (ProjectDocumentInfoContainer)imageHandler.getDocumentInfo(guid);

			// Output document start and end dates
			System.out.println("Project start date: "+ documentInfo.getStartDate());
			System.out.println("Project end date: "+ documentInfo.getEndDate());
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		//ExEnd:ObtainStartAndEndDateFromMSProjectDocument_19.1
	}
	/**
	 * Renders document as HTML with force password validation settings
	 * 
	 */
	public static void renderDocumentWithPasswordValidation(String DocumentName)
	{
		//ExStart:RenderDocumentAsHtmlWithPasswordValidation_19.1
		try
		{
			//Get Configurations
			ViewerConfig config = Utilities.getConfiguration();

			//Set the password to be validated on every call
			config.setForcePasswordValidation(true);

			// Create html handler
			ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);

			// Guid implies that unique document name 
			String guid = DocumentName;

			//Instantiate the HtmlOptions object
			HtmlOptions options = new HtmlOptions();

			// Set document password. The method is same for ImageOptions
			options.setPassword("password");

			//Get document pages in html form
			List<PageHtml> pages = htmlHandler.getPages(guid, options);

			for (PageHtml page : pages)
			{
				//Save each page at disk
				Utilities.saveAsHtml(page.getPageNumber() + "_" + DocumentName, page.getHtmlContent());
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		//ExEnd:RenderDocumentAsHtmlWithPasswordValidation_19.1
	}

	/**
	 * Renders document as HTML with force password validation settings
	 * 
	 */
	public static void renderDocumentWithForcePasswordValidation(String DocumentName)
	{
		//ExStart:RenderDocumentAsHtmlWithForcePasswordValidation_19.1
		try
		{
			//Get Configurations
			ViewerConfig config = Utilities.getConfiguration();

			//Set the password to be validated on every call
			config.setForcePasswordValidation(true);

			// Create html handler
			ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);
			htmlHandler.clearCache();
			// Guid implies that unique document name 
			String guid = DocumentName;

			//Instantiate the HtmlOptions object
			HtmlOptions options = new HtmlOptions();

			// Set document password
			options.setPassword("password");

			//Get document pages in html form
			List<PageHtml> pages = htmlHandler.getPages(guid, options);

			for (PageHtml page : pages)
			{
				//Save each page at disk
				Utilities.saveAsHtml(page.getPageNumber() + "_" + DocumentName, page.getHtmlContent());
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		//ExEnd:RenderDocumentAsHtmlWithForcePasswordValidation_19.1
	}
	/**
	 * Gets image attachment of email message
	 * 
	 */

	public static void getEmailAttachments(String DocumentName)
	{
		//ExStart:GetEmailAttachments_19.1
		try
		{
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Create image handler
			ViewerImageHandler handler = new ViewerImageHandler(config);


			//Instantiate Attachment 
			Attachment attachment = new Attachment(DocumentName, "attachment-image.png");

			// Get attachment original file
			FileContainer container = handler.getFile(attachment); 

			System.out.println("Attach name: "+ attachment.getName() +"Type: "+ attachment.getFileType());
			System.out.println("Attach stream length: "+ container.getStream().available());
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		//ExEnd:GetEmailAttachments_19.1
	}
	/**
	 * Gets html representation of attachment files
	 * 
	 */


	public static void renderEmailAttachmentHTMLRepresentation(String DocumentName)
	{
		//ExStart:GetEmailAttachmentHTMLRepresentation_19.1
		try
		{
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Setup html conversion options
			HtmlOptions htmlOptions = new HtmlOptions();
			htmlOptions.setEmbedResources(false);

			// Init viewer html handler
			ViewerHtmlHandler handler = new ViewerHtmlHandler(config);

			DocumentInfoContainer info = handler.getDocumentInfo(DocumentName);

			// Iterate over the attachments collection
			for (AttachmentBase attachment : info.getAttachments())
			{
				System.out.println("Attach name: "+ attachment.getName() +"Type: "+ attachment.getFileType());

				// Get attachment document html representation
				List<PageHtml> pages = handler.getPages(attachment, htmlOptions);
				for (PageHtml page : pages)
				{
					System.out.println("  Page: "+page.getPageNumber()+", size: "+ page.getHtmlContent().length());
					for (HtmlResource htmlResource : page.getHtmlResources())
					{
						InputStream resourceStream = handler.getResource(attachment, htmlResource);
						System.out.println("     Resource: "+htmlResource.getResourceName()+", size: "+ resourceStream.available() );
					}
				}
			}

		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		//ExEnd:GetEmailAttachmentHTMLRepresentation_19.1
	}
	/**
	 * Gets html representation of attachment files from Stream
	 * 
	 */

	public static void renderEmailAttachmentHTMLRepresentationFromStream(String DocumentName)
	{
		//ExStart:GetEmailAttachmentHTMLRepresentationFromStream_19.1
		try
		{

			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Setup html conversion options
			HtmlOptions htmlOptions = new HtmlOptions();
			htmlOptions.setEmbedResources(false);

			FileInputStream fileStream = Utilities.getDocumentStream(DocumentName);
			// Init viewer html handler
			ViewerHtmlHandler handler = new ViewerHtmlHandler(config);

			DocumentInfoContainer info = handler.getDocumentInfo(fileStream);

			// Iterate over the attachments collection
			for (AttachmentBase attachment : info.getAttachments())
			{
				System.out.println("Attach name: "+ attachment.getName() +"Type: "+ attachment.getFileType());

				// Get HTML representation of the attachment
				List<PageHtml> pages = handler.getPages( attachment, htmlOptions);
				for (PageHtml page : pages)
				{
					System.out.println("  Page: "+page.getPageNumber()+", size: "+ page.getHtmlContent().length());
					for (HtmlResource htmlResource : page.getHtmlResources())
					{
						InputStream resourceStream = handler.getResource(attachment, htmlResource);
						System.out.println("     Resource: "+htmlResource.getResourceName()+", size: "+ resourceStream.available() );
					}
				}
			}

		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		//ExEnd:GetEmailAttachmentHTMLRepresentationFromStream_19.1
	}
	/**
	 * Gets image representation of attachment files
	 * 
	 */

	public static void RenderEmailAttachment(String DocumentName)
	{
		//ExStart:GetEmailAttachmentImageRepresentation_19.1
		try
		{
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Init viewer image handler
			ViewerImageHandler handler = new ViewerImageHandler(config);

			DocumentInfoContainer info = handler.getDocumentInfo(DocumentName);

			// Iterate over the attachments collection
			for (AttachmentBase attachment : info.getAttachments())
			{
				System.out.println("Attach name: "+ attachment.getName() +"Type: "+ attachment.getFileType());

				// Get attachment document image representation
				List<PageImage> pages = handler.getPages(attachment);
				for (PageImage page : pages)
					System.out.println("  Page: "+page.getPageNumber()+", size: "+ page.getStream().available());
			}

		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		//ExEnd:GetEmailAttachmentImageRepresentation_19.1
	}
	/**
	 * Renders Outlook Data File documents with limit of items
	 * 
	 */
	public static void renderOutlookDataFileWithLimitOfItems(String DocumentName)
	{
		//ExStart:RenderOutlookDataFileWithLimitOfItems_19.1
		try
		{
			//  Get Configurations
			ViewerConfig config = Utilities.getConfiguration();

			ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);

			// Guid implies that unique document name 
			String guid = DocumentName;

			// Set Outlook options to render content with a specified limit
			HtmlOptions options = new HtmlOptions();
			options.getOutlookOptions().setMaxItemsInFolder(1000);

			// Get pages 
			List<PageHtml> pages = htmlHandler.getPages(guid, options);

			for (PageHtml page : pages)
			{
				//Save each page at disk
				Utilities.saveAsHtml(page.getPageNumber() + "_" + DocumentName, page.getHtmlContent());
			}
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		//ExEnd:RenderOutlookDataFileWithLimitOfItems_19.1
	}

	/**
	 *  Renders Outlook Data File documents with limit of items as PDF
	 * 
	 */
	public static void renderOutlookDataFileWithLimitOfItemsAsPDF(String DocumentName)
	{
		//ExStart:RenderOutlookDataFileWithLimitOfItemsAsPDF_19.1
		try
		{
			//Get Configurations
			ViewerConfig config = Utilities.getConfiguration();

			ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);

			// Guid implies that unique document name 
			String guid = DocumentName;

			// Set Project options to render number of items
			PdfFileOptions options = new PdfFileOptions();
			options.getOutlookOptions().setMaxItemsInFolder(1000);

			// Get PDF file 
			FileContainer fileContainer = htmlHandler.getPdfFile(guid, options);

			//Save file
			Utilities.saveAsFile(guid, fileContainer.getStream());
		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		//ExEnd:RenderOutlookDataFileWithLimitOfItemsAsPDF_19.1
	}
	/**
	 *  Gets email messages from Outlook Data Files
	 * 
	 */

	public static void getEmailAttachmentsFromOutlookDataFile(String DocumentName)
	{
		//ExStart:GetEmailAttachmentFromOutlookDataFile_19.1
		try
		{
			// Setup GroupDocs.Viewer config
			ViewerConfig config = Utilities.getConfiguration();

			// Init viewer image handler
			ViewerImageHandler handler = new ViewerImageHandler(config);

			DocumentInfoContainer info = handler.getDocumentInfo(DocumentName);

			// Iterate over the attachments collection
			for (AttachmentBase attachment : info.getAttachments())
			{
				// Print attachment name and file type
				System.out.println("Attach name: "+ attachment.getName() +"Type: "+ attachment.getFileType());

				// Get attachment's original file and print Stream's length
				FileContainer fileContainer = handler.getFile(attachment);

				System.out.println("Attach stream length: "+ fileContainer.getStream().available());

			}

		} catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
		//ExEnd:GetEmailAttachmentFromOutlookDataFile_19.1
	}
	/**
	 *  Renders email messages from Outlook Data Files
	 * 
	 */
	
    public static void renderEmailAttachmentsFromOutlookDataFile(String DocumentName)
    {
    	//ExStart:RenderEmailAttachmentFromOutlookDataFile_19.1
    	try
        {
           // Setup GroupDocs.Viewer config
            ViewerConfig config = Utilities.getConfiguration();

            // Init HTML handler
            ViewerHtmlHandler handler = new ViewerHtmlHandler(config);

            // Get document info
            DocumentInfoContainer info = handler.getDocumentInfo(DocumentName);

            // Iterate over the attachments collection
            for (AttachmentBase attachment : info.getAttachments())
            {
                // Get attachment's HTML representation
                List<PageHtml> pages = handler.getPages(attachment);

                // Iterate over the page collection
                for (PageHtml page : pages)
                {
                    // Save each page as HTML file
                    Utilities.saveAsHtml(page.getPageNumber() + "_" + DocumentName, page.getHtmlContent());
                }
            }
            
        } catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
      //ExEnd:RenderEmailAttachmentFromOutlookDataFile_19.1
    }
    /**
	 *  Gets layers' information of CAD documents
	 * 
	 */
    public static void getLayersInfoForCadDcouments(String DocumentName)
    {
        try
        {
            //ExStart:GetLayersInfoForCadDcouments_updated_19.1
            // Setup GroupDocs.Viewer config
            ViewerConfig config = Utilities.getConfiguration();

            // Create image handler
            ViewerImageHandler imageHandler = new ViewerImageHandler(config);
            String guid = DocumentName;

            CadDocumentInfoContainer documentInfo = (CadDocumentInfoContainer)imageHandler.getDocumentInfo(guid);

            // Loop through all layers contained in the drawing 
            for (String layer : documentInfo.getLayers())
            	System.out.println("Layer name: "+ layer);
            //ExEnd:GetLayersInfoForCadDcouments_updated_19.1
        } catch (Exception exp) {
			System.out.println("Exception: " + exp.getMessage());
			exp.printStackTrace();
		}
    }
    
    /**
	 *  Renders specific layer from CAD document
	 * 
	 */
  
    public static void renderSpecificLayerOfCADDocument(String DocumentName)
    {
    	try
    	{
    		//ExStart:RenderSpecificLayerOfCadDocument_19.1
    		// Get Configurations
    		ViewerConfig config = Utilities.getConfiguration();

    		// Create html handler
    		ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);
    		String guid = DocumentName;

    		// Set CAD options to render two Layers
    		HtmlOptions options = new HtmlOptions();
    		options.getCadOptions().getLayers().add("electrical");
    		options.getCadOptions().getLayers().add("walls");

    		// Get pages 
    		List<PageHtml> pages = htmlHandler.getPages(guid, options);

    		for (PageHtml page : pages)
    		{
    			//Save each page at disk
    			Utilities.saveAsHtml(page.getPageNumber() + "_" + DocumentName, page.getHtmlContent());
    		}
    		//ExEnd:RenderSpecificLayerOfCadDocument_19.1
    	} catch (Exception exp) {
    		System.out.println("Exception: " + exp.getMessage());
    		exp.printStackTrace();
    	}
    }
    /**
	 *  Renders document into Html with Enable Minification setting
	 * 
	 */
    
    public static void renderDocumentWithEnableMinification(String DocumentName)
    {
    	try
    	{
    		//ExStart:RenderDocumentAsHtmlWithEnableMinification_19.1
    		//Get Configurations
    		ViewerConfig config = Utilities.getConfiguration();

    		// Create html handler
    		ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);

    		// Guid implies that unique document name 
    		String guid = DocumentName;

    		//Instantiate the HtmlOptions object
    		HtmlOptions options = new HtmlOptions();

    		options.setEnableMinification(true);

    		//Get document pages in html form
    		List<PageHtml> pages = htmlHandler.getPages(guid, options);

    		for (PageHtml page : pages)
    		{
    			//Save each page at disk
    			Utilities.saveAsHtml(page.getPageNumber() + "_" + DocumentName, page.getHtmlContent());
    		}
    		//ExEnd:RenderDocumentAsHtmlWithEnableMinification_19.1
    	} catch (Exception exp) {
    		System.out.println("Exception: " + exp.getMessage());
    		exp.printStackTrace();
    	}
    }
    /**
	 *  Renders document into responsive html
	 * 
	 */
  
    public static void renderDocumentAsResponsiveHtml(String DocumentName)
    {
    	try
    	{
    		//ExStart:RenderDocumentAsResponsiveHtml_19.1
    		//Get Configurations
    		ViewerConfig config = Utilities.getConfiguration();

    		// Create html handler
    		ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);

    		// Guid implies that unique document name 
    		String guid = DocumentName;

    		//Instantiate the HtmlOptions object
    		HtmlOptions options = new HtmlOptions();

    		//to get html representations of pages with embedded resources
    		options.setEmbedResources(true);
    		options.setEnableResponsiveRendering(true);

    		//Get document pages in html form
    		List<PageHtml> pages = htmlHandler.getPages(guid, options);

    		for (PageHtml page : pages)
    		{
    			//Save each page at disk
    			Utilities.saveAsHtml(page.getPageNumber() + "_" + DocumentName, page.getHtmlContent());
    		}
    		//ExEnd:RenderDocumentAsResponsiveHtml_19.1
    	} catch (Exception exp) {
    		System.out.println("Exception: " + exp.getMessage());
    		exp.printStackTrace();
    	}
    }
    /**
	 *  Renders print area only in Excel document
	 * 
	 */

    public static void renderPrintAreaOnlyInExcel(String DocumentName)
    {
    	try
    	{

    		//ExStart:RenderPrintAreaOnlyInExcel_19.1
    		// Setup GroupDocs.Viewer config
    		ViewerConfig config = Utilities.getConfiguration();

    		ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);

    		// File guid
    		String guid = DocumentName;

    		// Enable redering of print area
    		HtmlOptions options = new HtmlOptions();
    		options.getCellsOptions().setRenderPrintAreaOnly(true);

    		List<PageHtml> pages = htmlHandler.getPages(guid, options);

    		for (PageHtml page : pages)
    		{
    			//Save each page at disk
    			Utilities.saveAsHtml(page.getPageNumber() + "_" + DocumentName, page.getHtmlContent());
    		}
    		//ExEnd:RenderPrintAreaOnlyInExcel_19.1
    	} catch (Exception exp) {
    		System.out.println("Exception: " + exp.getMessage());
    		exp.printStackTrace();
    	}
    }
    /**
	 *  Renders print area only in Excel document
	 * 
	 */
    
    public static void renderPrintAreaOnlyAsImageInExcel(String DocumentName)
    {
    	try
    	{
    		//ExStart:RenderPrintAreaOnlyAsImageInExcel_19.1
    		//Get Configurations
    		ViewerConfig config = Utilities.getConfiguration();

    		// Create html handler
    		ViewerImageHandler imageHandler = new ViewerImageHandler(config);

    		// Guid implies that unique document name 
    		String guid = DocumentName;

    		// Enable redering of print area
    		ImageOptions options = new ImageOptions();
    		options.getCellsOptions().setRenderPrintAreaOnly(true);

    		// Get pages 
    		List<PageImage> pages = imageHandler.getPages(guid, options);

    		for (PageImage page : pages)
    		{
    			// Save each image at disk
    			Utilities.saveAsImage(page.getPageNumber() + "_" + DocumentName,"png", page.getStream());
    		}
    		//ExEnd:RenderPrintAreaOnlyAsImageInExcel_19.1
    	} catch (Exception exp) {
    		System.out.println("Exception: " + exp.getMessage());
    		exp.printStackTrace();
    	}
    }
    /**
   	 *  Renders print area only in Excel documents as PDF
   	 * 
   	 */
    
    public static void renderPrintAreasInExcelAsPDF(String DocumentName)
    {
    	try
    	{
    		//ExStart:RenderPrintAreasInExcelAsPDF_19.1
    		//Get Configurations
    		ViewerConfig config = Utilities.getConfiguration();

    		// Create html handler
    		ViewerImageHandler imageHandler = new ViewerImageHandler(config);

    		// Guid implies that unique document name 
    		String guid = DocumentName;

    		// Enable redering of print area
    		PdfFileOptions options = new PdfFileOptions();
    		options.getCellsOptions().setRenderPrintAreaOnly(true);

    		// Get PDF file 
    		FileContainer fileContainer = imageHandler.getPdfFile(guid, options);

    		// Set file name
    		String filename = Path.getFileNameWithoutExtension(DocumentName) + ".pdf";

    		//Save file at disk
    		Utilities.saveAsFile(filename, fileContainer.getStream());
    		//ExEnd:RenderPrintAreasInExcelAsPDF_19.1
    	} catch (Exception exp) {
    		System.out.println("Exception: " + exp.getMessage());
    		exp.printStackTrace();
    	}
    }
    /**
   	 *  Renders hidden columsn and rows in Excel document
   	 * 
   	 */
    
    public static void renderHiddenContentInExcel(String DocumentName)
    {
    	try
    	{
    		//ExStart:RenderHiddenContentInExcel_19.1
    		// Setup GroupDocs.Viewer config
    		ViewerConfig config = Utilities.getConfiguration();

    		ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);

    		// File guid
    		String guid = DocumentName;

    		// Enable redering of hidden rows and columns
    		HtmlOptions options = new HtmlOptions();
    		options.getCellsOptions().setShowHiddenRows(true);
    		options.getCellsOptions().setShowHiddenColumns(true);

    		List<PageHtml> pages = htmlHandler.getPages(guid, options);

    		for (PageHtml page : pages)
    		{
    			//Save each page at disk
    			Utilities.saveAsHtml(page.getPageNumber() + "_" + DocumentName, page.getHtmlContent());
    		}
    		//ExEnd:RenderHiddenContentInExcel_19.1
    	} catch (Exception exp) {
    		System.out.println("Exception: " + exp.getMessage());
    		exp.printStackTrace();
    	}
    }
    /**
   	 *  Renders hidden column and rows in Excel document As Image
   	 * 
   	 */
  
    public static void renderHiddenContentAsImageInExcel(String DocumentName)
    {
    	try{
    		//ExStart:RenderHiddenContentAsImageInExcel_19.1
    		// Setup GroupDocs.Viewer config
    		ViewerConfig config = Utilities.getConfiguration();

    		// Create html handler
    		ViewerImageHandler imageHandler = new ViewerImageHandler(config);

    		// File guid
    		String guid = DocumentName;

    		// Enable redering of hidden rows and columns
    		ImageOptions options = new ImageOptions();
    		options.getCellsOptions().setShowHiddenRows(true);
    		options.getCellsOptions().setShowHiddenColumns(true);

    		// Get pages 
    		List<PageImage> pages = imageHandler.getPages(guid, options);

    		for (PageImage page : pages)
    		{
    			// Save each image at disk
    			Utilities.saveAsImage(page.getPageNumber() + "_" + DocumentName,"png", page.getStream());
    		}
    		//ExEnd:RenderHiddenContentAsImageInExcel_19.1
    	} catch (Exception exp) {
    		System.out.println("Exception: " + exp.getMessage());
    		exp.printStackTrace();
    	}
    }
    /**
   	 *  Renders hidden column and rows in Excel document As PDF
   	 * 
   	 */
    public static void renderHiddenContentInExcelAsPDF(String DocumentName)
    {
    	try{
    		//ExStart:RenderHiddenContentInExcelAsPDF_19.1
    		//Get Configurations
    		ViewerConfig config = Utilities.getConfiguration();

    		// Create html handler
    		ViewerImageHandler imageHandler = new ViewerImageHandler(config);

    		// Guid implies that unique document name 
    		String guid = DocumentName;

    		// Enable redering of hidden rows and columns
    		PdfFileOptions options = new PdfFileOptions();
    		options.getCellsOptions().setShowHiddenRows(true);
    		options.getCellsOptions().setShowHiddenColumns(true);

    		// Get PDF file 
    		FileContainer fileContainer = imageHandler.getPdfFile(guid, options);

    		// Set file name
    		String filename = Path.getFileNameWithoutExtension(DocumentName) + ".pdf";

    		//Save file at disk
    		Utilities.saveAsFile(filename, fileContainer.getStream());
    		//ExEnd:RenderHiddenContentInExcelAsPDF_19.1
    	} catch (Exception exp) {
    		System.out.println("Exception: " + exp.getMessage());
    		exp.printStackTrace();
    	}
    }
    /**
   	 *  Renders PDF document into Html with image quality settings
   	 * 
   	 */
  
    public static void renderPDFDocumentWithImageQuality(String DocumentName)
    {
    	try
    	{
    		//ExStart:RenderPDFDocumentAsHtmlWithImageQuality_19.1
    		// Get Configurations
    		ViewerConfig config = Utilities.getConfiguration();

    		// Create html handler
    		ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);

    		// Set guid
    		String guid = DocumentName;

    		// Instantiate HtmlOptions object
    		HtmlOptions options = new HtmlOptions();

    		//Set desired image quality in the output HTML document
    		HtmlOptions htmlOptions = new HtmlOptions();
    		htmlOptions.getPdfOptions().setImageQuality(ImageQuality.High);

    		// Get document pages in Html form
    		List<PageHtml> pages = htmlHandler.getPages(guid, options);

    		for (PageHtml page : pages)
    		{
    			//Save each page at disk
    			Utilities.saveAsHtml(page.getPageNumber() + "_" + DocumentName, page.getHtmlContent());
    		}
    		//ExEnd:RenderPDFDocumentAsHtmlWithImageQuality_19.1
    	} catch (Exception exp) {
    		System.out.println("Exception: " + exp.getMessage());
    		exp.printStackTrace();
    	}
    }
    /**
   	 *  Sets field labels when rendering email documents
   	 * 
   	 */
    public static void renderEmailMessageWithCustomFieldLabels(String DocumentName)
    {
    	try
    	{
    		//ExStart:ChangeFieldLabelsWhenRenderingEmailMessage_19.1
    		// Setup GroupDocs.Viewer config
    		ViewerConfig config = Utilities.getConfiguration();

    		ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);

    		// File guid
    		String guid = DocumentName;

    		// Set field labels 
    		HtmlOptions htmlOptions = new HtmlOptions();

    		//Initialize maps to set field labels 
    		Map<String,String> from=new HashMap<String, String>(){{put(EmailField.FROM,"Sender");}};
    		Map<String,String> to=new HashMap<String, String>(){{put(EmailField.TO,"Receiver");}};
    		Map<String,String> sent=new HashMap<String, String>(){{put(EmailField.SENT,"Date");}};
    		Map<String,String> subject=new HashMap<String, String>(){{put(EmailField.SUBJECT,"Topic");}};

    		htmlOptions.getEmailOptions().setFieldLabels(from);
    		htmlOptions.getEmailOptions().setFieldLabels(to);
    		htmlOptions.getEmailOptions().setFieldLabels(sent);
    		htmlOptions.getEmailOptions().setFieldLabels(subject);

    		// Render document with custom field labels
    		List<PageHtml> pages = htmlHandler.getPages(guid, htmlOptions);

    		for (PageHtml page : pages)
    		{
    			//Save each page at disk
    			Utilities.saveAsHtml(page.getPageNumber() + "_" + DocumentName, page.getHtmlContent());
    		}
    		//ExEnd:ChangeFieldLabelsWhenRenderingEmailMessage_19.1
    	} catch (Exception exp) {
    		System.out.println("Exception: " + exp.getMessage());
    		exp.printStackTrace();
    	}
    }
    /**
   	 *  Renders .msg file as image with page size settings
   	 * 
   	 */
  
    public static void renderEmailDocumentWithPageSizeSettings(String DocumentName)
    {
    	try
    	{
    		//ExStart:RenderEmailDocumentAsImageWithPageSizeSettings_19.1
    		// Setup GroupDocs.Viewer config
    		ViewerConfig config = Utilities.getConfiguration();

    		// Create html handler
    		ViewerImageHandler imageHandler = new ViewerImageHandler(config);

    		// File guid
    		String guid = DocumentName;

    		// Set page size
    		ImageOptions options = new ImageOptions();
    		options.getEmailOptions().setPageSize(PageSize.A4);

    		// Get pages 
    		List<PageImage> pages = imageHandler.getPages(guid, options);

    		for (PageImage page : pages)
    		{
    			// Save each image at disk
    			Utilities.saveAsImage(page.getPageNumber() + "_" + DocumentName,"png", page.getStream());
    		}
    		//ExEnd:RenderEmailDocumentAsImageWithPageSizeSettings_19.1
    	} catch (Exception exp) {
    		System.out.println("Exception: " + exp.getMessage());
    		exp.printStackTrace();
    	}
    }
    /**
   	 *  Renders .msg file as PDF with page size settings
   	 * 
   	 */
 
    public static void renderEmailDocumentAsPDFWithPageSizeSettings(String DocumentName)
    {
    	try
    	{
    		//ExStart:RenderEmailDocumentAsPDFWithPageSizeSettings_19.1
    		//Get Configurations
    		ViewerConfig config = Utilities.getConfiguration();

    		// Create html handler
    		ViewerImageHandler imageHandler = new ViewerImageHandler(config);

    		String guid = DocumentName;

    		// Set page size
    		PdfFileOptions options = new PdfFileOptions();
    		options.getEmailOptions().setPageSize( PageSize.A4);

    		// Get PDF file 
    		FileContainer fileContainer = imageHandler.getPdfFile(guid, options);

    		// Set file name
    		String filename = Path.getFileNameWithoutExtension(DocumentName) + ".pdf";

    		//Save file at disk
    		Utilities.saveAsFile(filename, fileContainer.getStream());
    		//ExEnd:RenderEmailDocumentAsPDFWithPageSizeSettings_19.1
    	} catch (Exception exp) {
    		System.out.println("Exception: " + exp.getMessage());
    		exp.printStackTrace();
    	}
    }
    /**
   	 *  Renders document into html excluding fonts
   	 * 
   	 */
 
    public static void renderDocumentExcludingFonts(String DocumentName)
    {
    	try
    	{
    		//ExStart:RenderDocumentAsHtmlExcludingFonts
    		//Get Configurations
    		ViewerConfig config = Utilities.getConfiguration();

    		// Create html handler
    		ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);

    		// Guid implies that unique document name 
    		String guid = DocumentName;

    		//Instantiate the HtmlOptions object
    		HtmlOptions options = new HtmlOptions();

    		options.setExcludeFonts(true);

    		//Get document pages in html form
    		List<PageHtml> pages = htmlHandler.getPages(guid, options);

    		for (PageHtml page : pages)
    		{
    			//Save each page at disk
    			Utilities.saveAsHtml(page.getPageNumber() + "_" + DocumentName, page.getHtmlContent());
    		}
    		//ExEnd:RenderDocumentAsHtmlExcludingFonts
    	} catch (Exception exp) {
    		System.out.println("Exception: " + exp.getMessage());
    		exp.printStackTrace();
    	}
    }
    /**
   	 *  Renders document into html excluding fonts list that are possibly installed on the underlying machine
   	 * 
   	 */
    
    public static void renderDocumentExcludingFontsList(String DocumentName)
    {
    	try
    	{
    		//ExStart:RenderDocumentAsHtmlExcludingFontsList_19.1
    		//Get Configurations
    		ViewerConfig config = Utilities.getConfiguration();

    		// Create html handler
    		ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);

    		// Guid implies that unique document name 
    		String guid = DocumentName;

    		//Instantiate the HtmlOptions object
    		HtmlOptions options = new HtmlOptions();

    		options.getExcludeFontsList().add("Times New Roman");
    		options.getExcludeFontsList().add("Arial");

    		//Get document pages in html form
    		List<PageHtml> pages = htmlHandler.getPages(guid, options);

    		for (PageHtml page : pages)
    		{
    			//Save each page at disk
    			Utilities.saveAsHtml(page.getPageNumber() + "_" + DocumentName, page.getHtmlContent());
    		}
    		//ExEnd:RenderDocumentAsHtmlExcludingFontsList_19.1
    	} catch (Exception exp) {
    		System.out.println("Exception: " + exp.getMessage());
    		exp.printStackTrace();
    	}
    }
    /**
   	 *  Renders Excel file as Html ignoring the empty rows
   	 * 
   	 */
 
    public static void renderExcelIgnoringEmptyRows(String DocumentName)
    {
    	try
    	{
    		//ExStart:RenderExcelAsHtmlIgnoringEmptyRows_19.1
    		ViewerConfig config = Utilities.getConfiguration();

    		// Create html handler
    		ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);
    		String guid = DocumentName;

    		// Set Cells options to ignore empty rows
    		HtmlOptions options = new HtmlOptions();
    		options.getCellsOptions().setIgnoreEmptyRows(true); // default value is false

    		// Get pages
    		List<PageHtml> pages = htmlHandler.getPages(guid, options);

    		for (PageHtml page : pages)
    		{
    			//Save each page at disk
    			Utilities.saveAsHtml(page.getPageNumber() + "_" + DocumentName, page.getHtmlContent());
    		}
    		//ExEnd:RenderExcelAsHtmlIgnoringEmptyRows_19.1
    	} catch (Exception exp) {
    		System.out.println("Exception: " + exp.getMessage());
    		exp.printStackTrace();
    	}
    }
    /**
   	 *  Render a document into html specifying resource prefix
   	 * 
   	 */
  
    public static void RenderDocumentWithResourcePrefix(String DocumentName)
    {
    	try
    	{
    		//ExStart:RenderDocumentAsHtmlWithResourcePrefix_19.1
    		//Get Configurations
    		ViewerConfig config = Utilities.getConfiguration();

    		//Create html handler
    		ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);

    		//Guid implies that unique document name 
    		String guid = DocumentName;

    		//Instantiate the HtmlOptions object
    		HtmlOptions options = new HtmlOptions();

    		//To get html representations of pages with embedded resources
    		options.setEmbedResources( false);

    		//Set resource prefix
    		options.setHtmlResourcePrefix("http://example.com/api/pages/{page-number}/resources/{resource-name}");
    		//The {page-number} and {resource-name} patterns will be replaced with current processing page number and resource name accordingly.

    		//To ignore resource prefix in CSS 
    		options.setIgnorePrefixInResources(true);

    		//Get document pages in html form
    		List<PageHtml> pages = htmlHandler.getPages(guid, options);

    		for (PageHtml page : pages)
    		{
    			//Save each page at disk
    			Utilities.saveAsHtml(page.getPageNumber() + "_" + DocumentName, page.getHtmlContent());
    		}
    		//ExEnd:RenderDocumentAsHtmlWithResourcePrefix_19.1
    	} catch (Exception exp) {
    		System.out.println("Exception: " + exp.getMessage());
    		exp.printStackTrace();
    	}

    }
    /**
   	 *  Render a document into html specifying resource prefix
   	 * 
   	 */
  
    public static void renderHiddenSlidesOfPowerPoint(String DocumentName)
    {
    	try
    	{
    		//ExStart:RenderHiddenSlidesOfPowerPointAsHtml_19.1
    		ViewerConfig config = Utilities.getConfiguration();

    		// Create html handler
    		ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);
    		String guid = DocumentName;

    		// Set html options to render hidden slides
    		HtmlOptions options = new HtmlOptions();
    		options.setShowHiddenPages(true);

    		DocumentInfoContainer container = htmlHandler.getDocumentInfo(guid);

    		for (PageData page : container.getPages())
    			System.out.println("Page number: "+page.getNumber()+", Page Name: "+page.getName()+", IsVisible: "+page.isVisible());

    		List<PageHtml> pages = htmlHandler.getPages(guid, options);

    		for (PageHtml page : pages)
    		{
    			//Save each page at disk
    			Utilities.saveAsHtml(page.getPageNumber() + "_" + DocumentName, page.getHtmlContent());
    		}
    		//ExEnd:RenderHiddenSlidesOfPowerPointAsHtml_19.1
    	} catch (Exception exp) {
    		System.out.println("Exception: " + exp.getMessage());
    		exp.printStackTrace();
    	}

    }
    /**
   	 *  Renders hidden pages of Visio file as html
   	 * 
   	 */
  
    public static void renderHiddenPagesOfVisio(String DocumentName)
    {
    	try
    	{
    		//ExStart:RenderHiddenPagesInVisioAsHtml_19.1
    		//Get Configurations
    		ViewerConfig config = Utilities.getConfiguration();

    		// Create html handler
    		ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);
    		String guid = DocumentName;

    		// Set html options to render hidden pages
    		HtmlOptions options = new HtmlOptions();
    		options.setShowHiddenPages(true);

    		DocumentInfoContainer container = htmlHandler.getDocumentInfo(guid);

    		for (PageData page : container.getPages())
    			System.out.println("Page number: "+page.getNumber()+", Page Name: "+page.getName()+", IsVisible: "+page.isVisible());

    		List<PageHtml> pages = htmlHandler.getPages(guid, options);

    		for (PageHtml page : pages)
    		{
    			//Save each page at disk
    			Utilities.saveAsHtml(page.getPageNumber() + "_" + DocumentName, page.getHtmlContent());
    		}
    		//ExEnd:RenderHiddenPagesInVisioAsHtml_19.1

    	} catch (Exception exp) {
    		System.out.println("Exception: " + exp.getMessage());
    		exp.printStackTrace();
    	}
    }
    /**
   	 *  Shows hidden sheets for Excel files in image representation
   	 * 
   	 */
  
    public static void renderHiddenSheetsInExcel(String DocumentName)
    {
    	try
    	{
    	//ExStart:RenderHiddenSheetsInExcel_19.1
    	// Setup GroupDocs.Viewer config
        ViewerConfig config = Utilities.getConfiguration();

        ViewerHtmlHandler htmlHandler = new ViewerHtmlHandler(config);

        // File guid
        String guid = DocumentName;

        // Set html options to render hidden sheets
        HtmlOptions options = new HtmlOptions();
        //do same while using ImageOptions
        options.setShowHiddenPages(true);

        List<PageHtml> pages = htmlHandler.getPages(guid, options);

        for (PageHtml page : pages)
        {
            //Save each page at disk
            Utilities.saveAsHtml(page.getPageNumber() + "_" + DocumentName, page.getHtmlContent());
        }
      //ExEnd:RenderHiddenSheetsInExcel_19.1
    	} catch (Exception exp) {
    		System.out.println("Exception: " + exp.getMessage());
    		exp.printStackTrace();
    	}
    }
    
}
